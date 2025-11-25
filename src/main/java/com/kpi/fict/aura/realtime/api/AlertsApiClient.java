package com.kpi.fict.aura.realtime.api;

import com.kpi.fict.aura.realtime.dto.AlertApiResponse;
import com.kpi.fict.aura.realtime.dto.AlertEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.time.Duration;
import java.util.List;

@Slf4j
@Component
public class AlertsApiClient implements ExternalApiClient<AlertEvent> {

    private final WebClient webClient;
    private final String alertsApiKey;

    public AlertsApiClient(WebClient.Builder builder, @Value("${api.alerts-key}") String alertsApiKey) {
        this.alertsApiKey = alertsApiKey;
        this.webClient = builder.baseUrl("https://alerts.in.ua").build();
    }

    @Override
    public List<AlertEvent> fetchEvents(String location) {
        try {
            AlertApiResponse response = webClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/api/v1/alerts")
                            .queryParam("apikey", alertsApiKey)
                            .queryParam("location", location)
                            .build())
                    .retrieve()
                    .bodyToMono(AlertApiResponse.class)
                    .timeout(Duration.ofSeconds(5))
                    .block();

            return response != null ? response.toEvents() : List.of();
        } catch (WebClientResponseException ex) {
            log.error("Alerts HTTP error: " + ex.getRawStatusCode() + " " + ex.getResponseBodyAsString());
        } catch (Exception ex) {
            log.error("Alerts unexpected error: {}", ex.getMessage());
        }
        return List.of();
    }

}