package com.spring.boot.corebackend.service.impl;

import com.spring.boot.corebackend.dtos.user.CreateUserProfileDto;
import com.spring.boot.corebackend.dtos.user.UserProfileDto;
import com.spring.boot.corebackend.entity.user.UserProfile;
import com.spring.boot.corebackend.mapper.UserProfileMapper;
import com.spring.boot.corebackend.repository.UserProfileRepository;
import com.spring.boot.corebackend.service.interfaces.UserProfileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserProfileServiceImpl implements UserProfileService {

    private final UserProfileRepository userProfileRepository;

    @Override
    public UserProfileDto getUserProfile(UUID userId) {

        log.info("➡️  [GET_USER_PROFILE] Request received | userId={}", userId);

        UserProfile userProfile = userProfileRepository.findById(userId)
                .orElseThrow(() -> {
                    log.error("❌ [GET_USER_PROFILE] User profile NOT FOUND | userId={}", userId);
                    return new IllegalStateException("User profile not found");
                });

        log.info(
                "✅ [GET_USER_PROFILE] User profile found | userId={} | type={}",
                userProfile.getUserId(),
                userProfile.getType()
        );

        return UserProfileMapper.toDto(userProfile);
    }

    @Override
    public UserProfileDto createUserProfile(CreateUserProfileDto dto, UUID userId) {

        log.info(
                "➡️  [CREATE_USER_PROFILE] Request received | userId={} | type={} | displayName={}",
                userId,
                dto.getType(),
                dto.getDisplayName()
        );

        // Validate input
        if (dto.getType() == null) {
            log.error(
                    "❌ [CREATE_USER_PROFILE] Validation failed | userId={} | reason=UserType is NULL",
                    userId
            );
            throw new IllegalArgumentException("User type is required");
        }

        // Check existence
        boolean exists = userProfileRepository.existsById(userId);
        log.debug(
                "🔍 [CREATE_USER_PROFILE] Checking existence | userId={} | exists={}",
                userId,
                exists
        );

        if (exists) {
            log.error(
                    "❌ [CREATE_USER_PROFILE] User profile already exists | userId={}",
                    userId
            );
            throw new IllegalStateException("User profile already exists");
        }

        // Build entity
        UserProfile userProfile = UserProfile.builder()
                .userId(userId)
                .displayName(dto.getDisplayName())
                .type(dto.getType())
                .createdAt(Instant.now())
                .build();

        log.debug(
                "🛠️  [CREATE_USER_PROFILE] UserProfile entity built | userId={} | type={}",
                userId,
                dto.getType()
        );

        // Persist
        UserProfile savedProfile = userProfileRepository.save(userProfile);

        log.info(
                "✅ [CREATE_USER_PROFILE] User profile CREATED successfully | userId={} | type={} | createdAt={}",
                savedProfile.getUserId(),
                savedProfile.getType(),
                savedProfile.getCreatedAt()
        );

        return UserProfileMapper.toDto(savedProfile);
    }
}
