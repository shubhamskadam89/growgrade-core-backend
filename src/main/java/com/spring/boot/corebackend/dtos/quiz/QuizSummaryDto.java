package com.spring.boot.corebackend.dtos.quiz;
import com.spring.boot.corebackend.dtos.user.UserSummaryDto;

import com.spring.boot.corebackend.entity.quiz.QuizLevel;
import com.spring.boot.corebackend.entity.quiz.QuizVisibility;
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
