package com.spring.boot.corebackend.dtos;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
@Builder
public class RoomDto {
    private UUID id;
    private QuizSummaryDto quiz;
    private UserSummaryDto createdBy;
    private boolean active;
    private Instant startedAt;
}
