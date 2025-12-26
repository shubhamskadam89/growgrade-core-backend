package com.spring.boot.corebackend.dtos.quiz;
import com.spring.boot.corebackend.dtos.user.UserSummaryDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class QuizAttemptResultDto {
    private UUID id;
    private QuizSummaryDto quiz;
    private UserSummaryDto user;
    private Integer score;
    private Integer timeTakenSeconds;
    private Instant startedAt;
    private Instant completedAt;
}
