package com.spring.boot.corebackend.controllers;

import com.spring.boot.corebackend.dtos.analytics.StudentQuizAnalyticsDto;
import com.spring.boot.corebackend.dtos.analytics.TeacherQuizAnalyticsDto;
import com.spring.boot.corebackend.service.interfaces.AnalyticsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/analytics")
@RequiredArgsConstructor
@Slf4j
public class AnalyticsController {

    private final AnalyticsService analyticsService;

    @GetMapping("/student")
    public Iterable<StudentQuizAnalyticsDto> getStudentAnalytics() {
        log.info("Request received to fetch student analytics.");
        Iterable<StudentQuizAnalyticsDto> analytics = analyticsService.getStudentAnalytics();
        log.info("Successfully fetched student analytics.");
        return analytics;
    }

    @GetMapping("/teacher")
    public Iterable<TeacherQuizAnalyticsDto> getTeacherAnalytics() {
        log.info("Request received to fetch teacher analytics.");
        Iterable<TeacherQuizAnalyticsDto> analytics = analyticsService.getTeacherAnalytics();
        log.info("Successfully fetched teacher analytics.");
        return analytics;
    }

    @GetMapping("/quiz/{quizId}")
    public TeacherQuizAnalyticsDto getQuizAnalytics(
            @PathVariable UUID quizId) {
        log.info("Request received to fetch analytics for quizId: {}", quizId);
        TeacherQuizAnalyticsDto analytics = analyticsService.getQuizAnalytics(quizId);
        log.info("Successfully fetched analytics for quizId: {}", quizId);
        return analytics;
    }
}
