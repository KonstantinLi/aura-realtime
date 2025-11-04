package com.kpi.fict.aura.realtime.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.receiver.ReceiverOptions;

import java.util.List;
import java.util.Map;

@Configuration
public class KafkaConfiguration {

    @Bean
    public KafkaReceiver<Object, Object> alertsReceiver() {
        Map<String, Object> props = Map.of(
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka:9092",
                ConsumerConfig.GROUP_ID_CONFIG, "realtime-service",
                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer",
                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer",
                ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest"
        );

        ReceiverOptions<Object, Object> receiverOptions =
                ReceiverOptions.create(props).subscription(
                        List.of("alerts-events", "risk-decisions")
                );

        return KafkaReceiver.create(receiverOptions);
    }

}