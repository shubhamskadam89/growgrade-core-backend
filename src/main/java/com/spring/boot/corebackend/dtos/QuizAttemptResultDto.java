package com.spring.boot.corebackend.dtos;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
@Builder
public class QuizAttemptResultDto {
    private UUID id;
    private QuizSummaryDto quiz;
    private UserSummaryDto user;
    private Integer score;
    private Integer timeTakenSeconds;
    private Instant startedAt;
    private Instant completedAt;
}
