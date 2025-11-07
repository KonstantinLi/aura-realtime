package com.kpi.fict.aura.realtime.dto;

import java.util.List;

public record EarthquakeApiResponse(List<EarthquakeData> data) {

    public List<EarthquakeEvent> toEvents() {
        return data.stream()
                .map(data -> new EarthquakeEvent(data.location(), data.magnitude(), data.time(), data.description()))
                .toList();
    }

    public record EarthquakeData(String location, double magnitude, String time, String description) {}

}