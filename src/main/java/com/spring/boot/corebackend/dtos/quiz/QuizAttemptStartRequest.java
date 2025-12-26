package com.spring.boot.corebackend.dtos.quiz;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuizAttemptStartRequest {
    private UUID quizId;
}
