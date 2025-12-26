package com.spring.boot.corebackend.service.impl;

import com.spring.boot.corebackend.dtos.room.PlayerJoinedEvent;
import com.spring.boot.corebackend.entity.room.ParticipantRole;
import com.spring.boot.corebackend.entity.room.Room;
import com.spring.boot.corebackend.entity.room.RoomParticipant;
import com.spring.boot.corebackend.entity.room.RoomStatus;
import com.spring.boot.corebackend.repository.RoomParticipantRepository;
import com.spring.boot.corebackend.repository.RoomRepository;
import com.spring.boot.corebackend.service.components.RoomEventPublisher;
import com.spring.boot.corebackend.service.interfaces.RoomSocketService;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoomSocketServiceImpl implements RoomSocketService {

    private final RoomRepository roomRepository;
    private final RoomParticipantRepository participantRepository;
    private final RoomEventPublisher eventPublisher;

    @Override
    public void joinRoom(String roomPin, String sessionUser) {

        Room room = roomRepository.findByRoomPin(roomPin)
                .orElseThrow(() -> new IllegalArgumentException("Room not found"));

        if (room.getStatus() != RoomStatus.LOBBY_OPEN) {
            throw new IllegalStateException("Room is not open");
        }

        // TEMP user identity (replace with JWT later)
        String displayName = sessionUser != null ? sessionUser : "Guest";

        // Persist participant
        // (later: map JWT user → UserProfile)
        participantRepository.save(
                RoomParticipant.builder()
                        .room(room)
                        .role(ParticipantRole.PLAYER)
                        .build());

        eventPublisher.publishEvent(
                room.getId(),
                new PlayerJoinedEvent("PLAYER_JOINED", displayName));

        log.info("User [{}] joined room [{}]", displayName, roomPin);
    }
}
