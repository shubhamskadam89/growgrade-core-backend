package com.spring.boot.corebackend.mapper;

import com.spring.boot.corebackend.dtos.room.RoomDto;
import com.spring.boot.corebackend.entity.room.Room;

public final class RoomMapper {

    private RoomMapper() {
    }

    public static RoomDto toDto(Room entity) {
        if (entity == null)
            return null;
        return RoomDto.builder()
                .id(entity.getId())
                .quiz(QuizMapper.toSummaryDto(entity.getQuiz()))
                .createdBy(UserProfileMapper.toSummaryDto(entity.getCreatedBy()))
                .active(entity.isActive())
                .startedAt(entity.getStartedAt())
                .build();
    }

    public static java.util.List<RoomDto> toDtos(java.util.Collection<Room> entities) {
        if (entities == null)
            return java.util.Collections.emptyList();
        return entities.stream()
                .map(RoomMapper::toDto)
                .collect(java.util.stream.Collectors.toList());
    }
}
