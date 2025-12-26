package com.spring.boot.corebackend.controllers;

import com.spring.boot.corebackend.dtos.room.JoinRoomCommand;
import com.spring.boot.corebackend.service.interfaces.RoomSocketService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class RoomWebSocketController {

    private final RoomSocketService roomSocketService;

    @MessageMapping("/rooms/join")
    public void joinRoom(
            JoinRoomCommand command,
            @Header("simpSessionId") String sessionId) {
        log.info("WS join request: session={}, pin={}", sessionId, command.roomPin());
        // Delegate to service to handle room joining logic
        roomSocketService.joinRoom(command.roomPin(), sessionId);
        log.info("WS join processed for session={}", sessionId);
    }
}
