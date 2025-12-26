package com.spring.boot.corebackend.controllers;

import com.spring.boot.corebackend.dtos.user.CreateUserProfileDto;
import com.spring.boot.corebackend.dtos.user.UserProfileDto;
import com.spring.boot.corebackend.service.interfaces.UserProfileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserProfileController {

    private final UserProfileService profileService;

    @GetMapping("/me")
    public ResponseEntity<UserProfileDto> getMyProfile(Authentication authentication) {
        java.util.UUID userId = (java.util.UUID) authentication.getPrincipal();
        log.info("Request received to fetch profile for userId: {}", userId);
        UserProfileDto profile = profileService.getUserProfile(userId);
        log.info("Successfully fetched profile for userId: {}", userId);
        return ResponseEntity.ok(profile);
    }

    @PostMapping("/me")
    public ResponseEntity<UserProfileDto> createMyProfile(
            @RequestBody CreateUserProfileDto request,
            Authentication authentication) {
        java.util.UUID userId = (java.util.UUID) authentication.getPrincipal();
        log.info("Request received to create profile for userId: {}", userId);
        UserProfileDto dto = profileService.createUserProfile(request, userId);
        log.info("Profile created successfully for userId: {}", userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }
}
