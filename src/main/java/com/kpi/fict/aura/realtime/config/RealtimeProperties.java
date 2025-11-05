package com.kpi.fict.aura.realtime.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "realtime")
public record RealtimeProperties(
        Api api) {

    public record Api(
            String alertsUri,
            String earthquakeUri,
            String weatherUri,
            String cameraUri) {}

}