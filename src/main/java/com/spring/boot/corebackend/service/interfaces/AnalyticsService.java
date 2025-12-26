package com.spring.boot.corebackend.service.interfaces;

import com.spring.boot.corebackend.dtos.analytics.StudentQuizAnalyticsDto;
import com.spring.boot.corebackend.dtos.analytics.TeacherQuizAnalyticsDto;

import java.util.UUID;

public interface AnalyticsService {

    Iterable<StudentQuizAnalyticsDto> getStudentAnalytics();

    Iterable<TeacherQuizAnalyticsDto> getTeacherAnalytics();

    TeacherQuizAnalyticsDto getQuizAnalytics(UUID quizId);
}
