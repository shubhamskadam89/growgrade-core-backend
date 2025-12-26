package com.spring.boot.corebackend.dtos.analytics;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TeacherQuizAnalyticsDto {
    private String quizId;
    private String quizTitle;
    private int totalAttempts;
    private double averageScore;
}
