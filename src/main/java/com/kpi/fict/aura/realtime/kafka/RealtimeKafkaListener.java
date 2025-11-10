package com.kpi.fict.aura.realtime.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kpi.fict.aura.realtime.dto.KafkaTopicDto;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.Disposable;

@Component
@RequiredArgsConstructor
public class RealtimeKafkaListener {

    private Disposable subscription;
    private final KafkaReceiverService receiverService;
    private final KafkaPublisherService publisherService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @PostConstruct
    public void start() {
        subscription = receiverService.receiveValues()
                .doOnNext(eventJson -> {
                    try {
                        publisherService.send(objectMapper.readValue(eventJson, KafkaTopicDto.class));
                    } catch (JsonProcessingException ex) {
                        throw new RuntimeException(ex);
                    }
                }).subscribe();
    }

    public void stop() {
        if (subscription != null && !subscription.isDisposed()) {
            subscription.dispose();
        }
    }

}