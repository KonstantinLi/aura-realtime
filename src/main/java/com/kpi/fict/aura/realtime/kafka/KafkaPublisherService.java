package com.kpi.fict.aura.realtime.kafka;

import com.kpi.fict.aura.realtime.dto.KafkaTopicDto;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderRecord;

@Service
@RequiredArgsConstructor
public class KafkaPublisherService {

    private final KafkaSender<String, String> kafkaSender;

    public Mono<Void> send(KafkaTopicDto dto) {
        SenderRecord<String, String, String> record = SenderRecord.create(
                new ProducerRecord<>(dto.topic(), dto.key(), dto.value()), dto.key());
        return kafkaSender.send(Mono.just(record)).then();
    }

}