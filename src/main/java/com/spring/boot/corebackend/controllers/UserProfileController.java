package com.spring.boot.corebackend.controllers;

import com.spring.boot.corebackend.dtos.CreateUserProfileDto;
import com.spring.boot.corebackend.dtos.UserProfileDto;
import com.spring.boot.corebackend.repository.UserProfileRepository;
import com.spring.boot.corebackend.service.interfaces.UserProfileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    public ResponseEntity<UserProfileDto> getMyProfile(
            @RequestHeader("X-User-Id") java.util.UUID userId) {
        log.info("Received request for get profile");
        return ResponseEntity.ok(profileService.getUserProfile(userId));
    }

    @PostMapping("/me")
    public ResponseEntity<UserProfileDto> createMyProfile(
            @RequestBody CreateUserProfileDto request,
            @RequestHeader("X-User-Id") java.util.UUID userId) {
        log.info("Received request for create profile");
        UserProfileDto dto = profileService.createUserProfile(request, userId);
        log.info("profile created");
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }
}
