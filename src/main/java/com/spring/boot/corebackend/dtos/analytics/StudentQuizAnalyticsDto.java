package com.spring.boot.corebackend.dtos.analytics;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StudentQuizAnalyticsDto {
    private String quizId;
    private String quizTitle;
    private int score;
    private int totalQuestions;
}
