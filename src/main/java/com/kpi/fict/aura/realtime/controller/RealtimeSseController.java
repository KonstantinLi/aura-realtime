package com.kpi.fict.aura.realtime.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/realtime/sse")
public class RealtimeSseController {

    private final RealtimeEventService realtimeEventService;

    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Object> streamEvents() {
        return realtimeEventService.streamEvents();
    }

}