package com.spring.boot.corebackend.service.impl;

import com.spring.boot.corebackend.dtos.quiz.QuizAttemptResultDto;
import com.spring.boot.corebackend.dtos.quiz.QuizAttemptStartRequest;
import com.spring.boot.corebackend.dtos.quiz.SubmitQuizAttemptDto;
import com.spring.boot.corebackend.service.interfaces.QuizAttemptService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class QuizAttemptServiceImpl implements QuizAttemptService {
    @Override
    public QuizAttemptResultDto startAttempt(QuizAttemptStartRequest request) {
        return QuizAttemptResultDto.builder().build();
    }

    @Override
    public QuizAttemptResultDto submitAttempt(SubmitQuizAttemptDto request) {
        return QuizAttemptResultDto.builder().build();
    }

    @Override
    public List<QuizAttemptResultDto> getMyAttempts() {
        return Collections.emptyList();
    }

    @Override
    public List<QuizAttemptResultDto> getAttemptsForQuiz(UUID quizId) {
        return Collections.emptyList();
    }
}
