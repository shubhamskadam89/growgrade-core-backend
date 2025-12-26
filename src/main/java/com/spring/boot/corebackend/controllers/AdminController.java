package com.spring.boot.corebackend.controllers;

import com.spring.boot.corebackend.dtos.quiz.QuizSummaryDto;
import com.spring.boot.corebackend.dtos.user.UserSummaryDto;
import com.spring.boot.corebackend.service.interfaces.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/users")
    public Iterable<UserSummaryDto> getAllUsers() {
        log.info("Request received to fetch all users.");
        Iterable<UserSummaryDto> users = adminService.getAllUsers();
        log.info("Successfully fetched all users.");
        return users;
    }

    @GetMapping("/quizzes")
    public Iterable<QuizSummaryDto> getAllQuizzes() {
        log.info("Request received to fetch all quizzes.");
        Iterable<QuizSummaryDto> quizzes = adminService.getAllQuizzes();
        log.info("Successfully fetched all quizzes.");
        return quizzes;
    }
}
