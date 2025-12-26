package com.spring.boot.corebackend.controllers;

import com.spring.boot.corebackend.dtos.quiz.QuizCreationRequest;
import com.spring.boot.corebackend.dtos.quiz.QuizDetailDto;
import com.spring.boot.corebackend.dtos.quiz.QuizSummaryDto;
import com.spring.boot.corebackend.service.interfaces.QuizService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/quizzes")
@RequiredArgsConstructor
@Slf4j
public class QuizController {

    private final QuizService quizService;

    @PostMapping
    public QuizSummaryDto createQuiz(
            @RequestBody QuizCreationRequest request, Authentication authentication ) {
        java.util.UUID userId = (java.util.UUID) authentication.getPrincipal();
        log.info("Request received to create a quiz: {}", request.getTitle());
        QuizSummaryDto createdQuiz = quizService.createQuiz(request,userId);
        log.info("Quiz created successfully: {}", createdQuiz.getId());
        return createdQuiz;
    }

    @GetMapping("/{quizId}")
    public QuizDetailDto getQuizDetail(
            @PathVariable UUID quizId) {
        log.info("Request received to fetch details for quizId: {}", quizId);
        QuizDetailDto quizDetail = quizService.getQuizDetail(quizId);
        log.info("Successfully fetched details for quizId: {}", quizId);
        return quizDetail;
    }

    @GetMapping("/{quizId}/play")
    public QuizDetailDto getPlayableQuiz(
            @PathVariable UUID quizId) {
        log.info("Request received to fetch playable quiz for quizId: {}", quizId);
        QuizDetailDto playableQuiz = quizService.getPlayableQuiz(quizId);
        log.info("Successfully fetched playable quiz for quizId: {}", quizId);
        return playableQuiz;
    }

    @GetMapping
    public List<QuizSummaryDto> getMyQuizzes() {
        log.info("Request received to fetch my quizzes.");
        List<QuizSummaryDto> quizzes = quizService.getMyQuizzes();
        log.info("Successfully fetched my quizzes.");
        return quizzes;
    }

    @DeleteMapping("/{quizId}")
    public void deleteQuiz(
            @PathVariable UUID quizId) {
        log.info("Request received to delete quizId: {}", quizId);
        quizService.deleteQuiz(quizId);
        log.info("Successfully deleted quizId: {}", quizId);
    }
}
