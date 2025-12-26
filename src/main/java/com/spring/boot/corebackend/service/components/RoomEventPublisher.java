package com.spring.boot.corebackend.service.components;

import com.spring.boot.corebackend.dtos.room.RoomEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class RoomEventPublisher {

    private final SimpMessagingTemplate messagingTemplate;

    public void publishEvent(UUID roomId, RoomEvent event) {
        messagingTemplate.convertAndSend(
                "/topic/rooms/" + roomId + "/events",
                event);
    }
}
