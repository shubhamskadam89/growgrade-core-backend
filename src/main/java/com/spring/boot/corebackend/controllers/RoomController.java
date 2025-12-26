package com.spring.boot.corebackend.controllers;

import com.spring.boot.corebackend.dtos.room.CreateRoomRequest;
import com.spring.boot.corebackend.dtos.room.CreateRoomResponse;
import com.spring.boot.corebackend.entity.room.Room;
import com.spring.boot.corebackend.service.components.RoomService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

import java.util.UUID;

@RestController
@RequestMapping("/api/rooms")
@RequiredArgsConstructor
@Slf4j
public class RoomController {

        private final RoomService roomService;

        @PostMapping
        public ResponseEntity<CreateRoomResponse> createRoom(
                @RequestBody CreateRoomRequest request,
                Authentication authentication) {
            UUID uuid = (UUID) authentication.getPrincipal();
                log.info("Request received to create room for quizId: {}", request.quizId());
                Room room = roomService.createRoom(request.quizId(),uuid);
                log.info("Room created successfully with pin: {}", room.getRoomPin());

                return ResponseEntity.ok(
                                new CreateRoomResponse(
                                                room.getId(),
                                                room.getRoomPin()));
        }
}
