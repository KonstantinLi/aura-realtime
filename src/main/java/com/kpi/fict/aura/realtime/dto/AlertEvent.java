package com.kpi.fict.aura.realtime.dto;

public record AlertEvent(
        String location,
        String alertType,
        String description,
        String severity) {}