package com.kpi.fict.aura.realtime.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;
import org.springframework.web.reactive.socket.server.support.WebSocketHandlerAdapter;

import java.util.Map;

@Configuration
public class WebsocketConfiguration {

    @Bean
    public SimpleUrlHandlerMapping webSocketMapping(RealtimeWebSocketHandler handler) {
        return new SimpleUrlHandlerMapping(
                Map.of("/ws/realtime", handler),
                10
        );
    }

    @Bean
    public WebSocketHandlerAdapter handlerAdapter() {
        return new WebSocketHandlerAdapter();
    }

}
