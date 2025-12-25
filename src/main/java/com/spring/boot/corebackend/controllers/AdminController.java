package com.spring.boot.corebackend.controllers;

import com.spring.boot.corebackend.dtos.UserSummaryDto;
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
    public Iterable<UserSummaryDto> getAllUsers(){
        log.info("Fetch request for all users");
        return adminService.getAllUsers();

    }
//
//    @GetMapping("/quizzes")
//    public List<QuizSummaryDto> getAllQuizzes();
}
