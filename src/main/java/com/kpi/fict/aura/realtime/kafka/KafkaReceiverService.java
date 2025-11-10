package com.kpi.fict.aura.realtime.kafka;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.receiver.ReceiverRecord;

@Service
@RequiredArgsConstructor
public class KafkaReceiverService {

    private final KafkaReceiver<String, String> kafkaReceiver;

    public Flux<ReceiverRecord<String, String>> receiveEvents() {
        return kafkaReceiver.receive()
                .doOnNext(r -> r.receiverOffset().acknowledge())
                .doOnError(err -> System.err.println("Kafka receive error: " + err.getMessage()));
    }

    public Flux<String> receiveValues() {
        return receiveEvents().map(ConsumerRecord::value);
    }

}