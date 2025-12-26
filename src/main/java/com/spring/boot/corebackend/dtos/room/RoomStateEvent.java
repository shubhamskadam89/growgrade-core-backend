package com.spring.boot.corebackend.dtos.room;
public record RoomStateEvent(
        String type,
        int participantCount,
        String status
) implements RoomEvent {}
