package com.spring.boot.corebackend.dtos;

import com.spring.boot.corebackend.entity.QuizLevel;
import com.spring.boot.corebackend.entity.QuizVisibility;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
@Builder
public class QuizSummaryDto {
    private UUID id;
    private String title;
    private UserSummaryDto createdBy;
    private QuizVisibility visibility;
    private QuizLevel level;
    private Integer timeLimitSeconds;
    private Integer totalScore;
    private Instant createdAt;
    private Instant scheduledAt;
}
