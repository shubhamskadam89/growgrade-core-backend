package com.spring.boot.corebackend.service.impl;

import com.spring.boot.corebackend.dtos.quiz.QuizCreationRequest;
import com.spring.boot.corebackend.dtos.quiz.QuizDetailDto;
import com.spring.boot.corebackend.dtos.quiz.QuizSummaryDto;
import com.spring.boot.corebackend.service.interfaces.QuizService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class QuizServiceImpl implements QuizService {
    @Override
    public QuizSummaryDto createQuiz(QuizCreationRequest request) {
        return QuizSummaryDto.builder().build();
    }

    @Override
    public QuizDetailDto getQuizDetail(UUID quizId) {
        return QuizDetailDto.builder().build();
    }

    @Override
    public QuizDetailDto getPlayableQuiz(UUID quizId) {
        return QuizDetailDto.builder().build();
    }

    @Override
    public List<QuizSummaryDto> getMyQuizzes() {
        return Collections.emptyList();
    }

    @Override
    public void deleteQuiz(UUID quizId) {

    }
}
