package com.spring.boot.corebackend.dtos.quiz;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class SubmitQuizAttemptDto {
    private UUID quizId;
    private List<AnswerDto> answers;

    @Data
    @Builder
    public static class AnswerDto {
        private UUID questionId;
        private UUID selectedOptionId;
    }
}
