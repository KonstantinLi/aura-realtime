package com.kpi.fict.aura.realtime.dto;

import java.util.List;

public record AlertApiResponse(List<AlertData> data) {

    public List<AlertEvent> toEvents() {
        return data.stream()
                .map(data -> new AlertEvent(data.location(), data.type(), data.description(), data.severity()))
                .toList();
    }

    public record AlertData(String location, String type, String description, String severity) {}

}