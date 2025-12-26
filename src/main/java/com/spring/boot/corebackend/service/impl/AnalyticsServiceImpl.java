package com.spring.boot.corebackend.service.impl;

import com.spring.boot.corebackend.dtos.analytics.StudentQuizAnalyticsDto;
import com.spring.boot.corebackend.dtos.analytics.TeacherQuizAnalyticsDto;
import com.spring.boot.corebackend.service.interfaces.AnalyticsService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.UUID;

@Service
public class AnalyticsServiceImpl implements AnalyticsService {
    @Override
    public Iterable<StudentQuizAnalyticsDto> getStudentAnalytics() {
        return Collections.emptyList();
    }

    @Override
    public Iterable<TeacherQuizAnalyticsDto> getTeacherAnalytics() {
        return Collections.emptyList();
    }

    @Override
    public TeacherQuizAnalyticsDto getQuizAnalytics(UUID quizId) {
        return TeacherQuizAnalyticsDto.builder().build();
    }
}
