package com.kpi.fict.aura.realtime.api;

import com.kpi.fict.aura.realtime.dto.WeatherApiResponse;
import com.kpi.fict.aura.realtime.dto.WeatherEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.time.Duration;
import java.util.List;

@Slf4j
@Component
public class WeatherApiClient implements ExternalApiClient<WeatherEvent> {

    private final WebClient webClient;
    private final String weatherApiKey;

    public WeatherApiClient(WebClient.Builder builder, @Value("${api.weather-key}") String weatherApiKey) {
        this.weatherApiKey = weatherApiKey;
        this.webClient = builder.baseUrl("https://api.weather.com").build();
    }

    @Override
    public List<WeatherEvent> fetchEvents(String location) {
        try {
            WeatherApiResponse response = webClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/v3/weather/forecast")
                            .queryParam("location", location)
                            .queryParam("apikey", weatherApiKey)
                            .build())
                    .retrieve()
                    .bodyToMono(WeatherApiResponse.class)
                    .timeout(Duration.ofSeconds(5))
                    .block();
            return response != null ? response.toEvents() : List.of();
        } catch (WebClientResponseException ex) {
            log.error("Weather HTTP error: " + ex.getRawStatusCode() + " " + ex.getResponseBodyAsString());
        } catch (Exception ex) {
            log.error("Weather unexpected error: {}", ex.getMessage());
        }
        return List.of();
    }

}