package com.spring.boot.corebackend.service.interfaces;

import com.spring.boot.corebackend.dtos.quiz.QuizCreationRequest;
import com.spring.boot.corebackend.dtos.quiz.QuizDetailDto;
import com.spring.boot.corebackend.dtos.quiz.QuizSummaryDto;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.UUID;

public interface QuizService {
    QuizSummaryDto createQuiz(QuizCreationRequest request, UUID userId);

    QuizDetailDto getQuizDetail(UUID quizId);

    QuizDetailDto getPlayableQuiz(UUID quizId);

    List<QuizSummaryDto> getMyQuizzes();

    void deleteQuiz(UUID quizId);
}
