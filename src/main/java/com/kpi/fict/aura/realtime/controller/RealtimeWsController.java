package com.kpi.fict.aura.realtime.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class RealtimeWsController {

    private final RealtimeEventService realtimeEventService;

    @MessageMapping("/subscribe")
    @SendTo("/topic/events")
    public Object subscribeEvents(String location) {
        return realtimeEventService.getLatestEvents(location);
    }

    @MessageMapping("/stepup")
    @SendTo("/topic/stepup")
    public Object stepUpNotification(Long userId) {
        return realtimeEventService.notifyStepUp(userId);
    }

}