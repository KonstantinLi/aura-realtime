package com.kpi.fict.aura.realtime.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = {AuraRealtimeConfiguration.REALTIME_BASE_PACKAGE})
@ComponentScan(basePackages = {AuraRealtimeConfiguration.REALTIME_BASE_PACKAGE})
@EnableJpaRepositories(basePackages = {AuraRealtimeConfiguration.REALTIME_BASE_PACKAGE})
@ConfigurationPropertiesScan(basePackages = {AuraRealtimeConfiguration.REALTIME_BASE_PACKAGE})
@Import({
        WebsocketConfiguration.class,
        KafkaConfiguration.class, RedisConfiguration.class,
        SecurityConfiguration.class, WebsocketConfiguration.class})
public class AuraRealtimeConfiguration {

    public static final String REALTIME_BASE_PACKAGE = "com.kpi.fict.aura.realtime";

}
