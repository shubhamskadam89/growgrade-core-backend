package com.spring.boot.corebackend.dtos.room;

public record PlayerJoinedEvent(
        String type,
        String displayName
) implements RoomEvent {}

