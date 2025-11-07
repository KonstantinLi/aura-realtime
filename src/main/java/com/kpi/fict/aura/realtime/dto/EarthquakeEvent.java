package com.kpi.fict.aura.realtime.dto;

public record EarthquakeEvent(
        String location,
        double magnitude,
        String time,
        String description) {}