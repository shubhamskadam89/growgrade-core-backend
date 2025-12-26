package com.spring.boot.corebackend.service.interfaces;

import com.spring.boot.corebackend.dtos.quiz.QuizAttemptResultDto;
import com.spring.boot.corebackend.dtos.quiz.QuizAttemptStartRequest;
import com.spring.boot.corebackend.dtos.quiz.SubmitQuizAttemptDto;

import java.util.List;
import java.util.UUID;

public interface QuizAttemptService {
    QuizAttemptResultDto startAttempt(QuizAttemptStartRequest request);

    QuizAttemptResultDto submitAttempt(SubmitQuizAttemptDto request);

    List<QuizAttemptResultDto> getMyAttempts();

    List<QuizAttemptResultDto> getAttemptsForQuiz(UUID quizId);
}
