package com.kpi.fict.aura.realtime.dto;

import java.util.List;

public record WeatherApiResponse(
        String location,
        List<String> forecasts) {

    public List<WeatherEvent> toEvents() {
        return forecasts.stream().map(forecast -> new WeatherEvent(location, forecast)).toList();
    }

}