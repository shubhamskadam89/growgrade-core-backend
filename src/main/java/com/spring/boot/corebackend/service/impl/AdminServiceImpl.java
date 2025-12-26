package com.spring.boot.corebackend.service.impl;

import com.spring.boot.corebackend.dtos.quiz.QuizSummaryDto;
import com.spring.boot.corebackend.dtos.user.UserProfileDto;
import com.spring.boot.corebackend.dtos.user.UserSummaryDto;
import com.spring.boot.corebackend.entity.user.UserProfile;
import com.spring.boot.corebackend.mapper.UserProfileMapper;
import com.spring.boot.corebackend.repository.UserProfileRepository;
import com.spring.boot.corebackend.service.interfaces.AdminService;
import com.spring.boot.corebackend.service.interfaces.UserProfileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminServiceImpl  implements AdminService {

    private final UserProfileRepository userProfileRepository;
    private final UserProfileServiceImpl userProfileServiceImpl;

    @Override
    public Iterable<UserSummaryDto> getAllUsers() {
        log.info("Fetching all users");
        List<UserProfile> users = userProfileRepository.findAll();
        log.info("Found "+users.size() +" users in database");
        return users.stream().map(UserProfileMapper::toSummaryDto).toList();
    }

    @Override
    public Iterable<QuizSummaryDto> getAllQuizzes() {
        return null;
    }
}
