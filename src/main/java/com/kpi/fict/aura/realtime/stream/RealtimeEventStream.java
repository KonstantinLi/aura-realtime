package com.kpi.fict.aura.realtime.stream;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

@Component
@RequiredArgsConstructor
public class RealtimeEventStream {

    private final Flux<String> flux;
    private final Sinks.Many<String> sink;

    public void emit(String eventJson) {
        sink.tryEmitNext(eventJson).orThrow();
    }

    public Flux<String> getStream() {
        return flux;
    }

}