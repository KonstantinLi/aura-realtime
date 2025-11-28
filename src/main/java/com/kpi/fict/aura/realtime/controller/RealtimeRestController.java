package com.kpi.fict.aura.realtime.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/realtime")
public class RealtimeRestController {

    private final RealtimeEventService realtimeEventService;

    @GetMapping(value = "/widgets", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> getWidgets(@RequestParam String location) {
        return realtimeEventService.getWidgetsData(location);
    }

    @GetMapping(value = "/history", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Object> getHistory(@RequestParam String location) {
        return realtimeEventService.getEventHistory(location);
    }

}