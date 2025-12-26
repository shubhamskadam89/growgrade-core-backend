package com.spring.boot.corebackend.config.websocket;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class WebSocketSessionRegistry {

    private final Map<String, Set<String>> roomSessions = new ConcurrentHashMap<>();

    public void register(String roomId, String sessionId) {
        roomSessions
                .computeIfAbsent(roomId, k -> ConcurrentHashMap.newKeySet())
                .add(sessionId);
    }

    public void unregister(String roomId, String sessionId) {
        Set<String> sessions = roomSessions.get(roomId);
        if (sessions != null) {
            sessions.remove(sessionId);
        }
    }

    public int count(String roomId) {
        return roomSessions.getOrDefault(roomId, Set.of()).size();
    }
}
