package com.kpi.fict.aura.realtime.stream;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
@RequiredArgsConstructor
public class RealtimeEventPublisher {

    private final RealtimeEventStream eventStream;

    public void publish(String eventJson) {
        eventStream.emit(eventJson);
    }

    public Flux<String> subscribe(String userId) {
        return eventStream.getStream()
                .filter(eventJson -> RealtimeEventFilter.allow(eventJson, userId));
    }

}