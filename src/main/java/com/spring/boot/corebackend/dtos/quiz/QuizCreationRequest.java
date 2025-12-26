package com.spring.boot.corebackend.dtos.quiz;

import com.spring.boot.corebackend.entity.quiz.QuizLevel;
import com.spring.boot.corebackend.entity.quiz.QuizVisibility;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
@Builder
public class QuizCreationRequest {
    private String title;
    private QuizVisibility visibility;
    private QuizLevel level;
    private Integer timeLimitSeconds;
    private Integer totalScore;
    private Instant scheduledAt;
    private List<QuizQuestionCreateDto> questions;
}
