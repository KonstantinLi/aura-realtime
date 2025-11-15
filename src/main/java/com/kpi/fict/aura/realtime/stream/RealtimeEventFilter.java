package com.kpi.fict.aura.realtime.stream;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RealtimeEventFilter {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static boolean allow(String eventJson, String userId) {
        try {
            JsonNode node = OBJECT_MAPPER.readTree(eventJson);
            String targetUser = node.has("userId") ? node.get("userId").asText() : "";
            return targetUser.equals(userId);
        } catch (Exception e) {
            return false;
        }
    }

}