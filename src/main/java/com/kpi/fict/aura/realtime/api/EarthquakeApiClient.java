package com.kpi.fict.aura.realtime.api;

import com.kpi.fict.aura.realtime.dto.EarthquakeApiResponse;
import com.kpi.fict.aura.realtime.dto.EarthquakeEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.time.Duration;
import java.util.List;

@Slf4j
@Component
public class EarthquakeApiClient implements ExternalApiClient<EarthquakeEvent> {

    private final WebClient webClient;
    private final String earthquakeApiKey;

    public EarthquakeApiClient(WebClient.Builder builder, @Value("${api.earthquake-key}") String earthquakeApiKey) {
        this.earthquakeApiKey = earthquakeApiKey;
        this.webClient = builder.baseUrl("https://earthquake.api.com").build();
    }

    @Override
    public List<EarthquakeEvent> fetchEvents(String location) {
        try {
            EarthquakeApiResponse response = webClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/v1/earthquakes")
                            .queryParam("apikey", earthquakeApiKey)
                            .queryParam("region", location)
                            .build())
                    .retrieve()
                    .bodyToMono(EarthquakeApiResponse.class)
                    .timeout(Duration.ofSeconds(5))
                    .block();

            return response != null ? response.toEvents() : List.of();
        } catch (WebClientResponseException ex) {
            log.error("Earthquake HTTP error: " + ex.getRawStatusCode() + " " + ex.getResponseBodyAsString());
        } catch (Exception ex) {
            log.error("Earthquake unexpected error: {}", ex.getMessage());
        }
        return List.of();
    }

}