package com.spring.boot.corebackend.controllers;

import com.spring.boot.corebackend.dtos.quiz.QuizAttemptResultDto;
import com.spring.boot.corebackend.dtos.quiz.QuizAttemptStartRequest;
import com.spring.boot.corebackend.dtos.quiz.SubmitQuizAttemptDto;
import com.spring.boot.corebackend.service.interfaces.QuizAttemptService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/attempts")
@RequiredArgsConstructor
@Slf4j
public class QuizAttemptController {

    private final QuizAttemptService attemptService;

    @PostMapping("/start")
    public QuizAttemptResultDto startAttempt(
            @RequestBody QuizAttemptStartRequest request) {
        log.info("Request received to start quiz attempt for quizId: {}", request.getQuizId());
        QuizAttemptResultDto result = attemptService.startAttempt(request);
        log.info("Quiz attempt started successfully.");
        return result;
    }

    @PostMapping("/submit")
    public QuizAttemptResultDto submitAttempt(
            @RequestBody SubmitQuizAttemptDto request) {
        log.info("Request received to submit quiz attempt for quizId: {}", request.getQuizId());
        QuizAttemptResultDto result = attemptService.submitAttempt(request);
        log.info("Quiz attempt submitted successfully.");
        return result;
    }

    @GetMapping("/me")
    public List<QuizAttemptResultDto> getMyAttempts() {
        log.info("Request received to fetch my quiz attempts.");
        List<QuizAttemptResultDto> attempts = attemptService.getMyAttempts();
        log.info("Successfully fetched my quiz attempts.");
        return attempts;
    }

    @GetMapping("/quiz/{quizId}")
    public List<QuizAttemptResultDto> getAttemptsForQuiz(
            @PathVariable UUID quizId) {
        log.info("Request received to fetch attempts for quizId: {}", quizId);
        List<QuizAttemptResultDto> attempts = attemptService.getAttemptsForQuiz(quizId);
        log.info("Successfully fetched attempts for quizId: {}", quizId);
        return attempts;
    }
}
