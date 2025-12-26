package com.spring.boot.corebackend.mapper;

import com.spring.boot.corebackend.dtos.user.UserProfileDto;
import com.spring.boot.corebackend.dtos.user.UserSummaryDto;
import com.spring.boot.corebackend.entity.user.UserProfile;

public final class UserProfileMapper {

    private UserProfileMapper() {
    }

    public static UserSummaryDto toSummaryDto(UserProfile entity) {
        if (entity == null)
            return null;
        return UserSummaryDto.builder()
                .userId(entity.getUserId()) // id renamed to userId in DTO
                .displayName(entity.getDisplayName())
                .type(entity.getType())
                .build();
    }

    public static UserProfileDto toDto(UserProfile entity) {
        if (entity == null)
            return null;
        return UserProfileDto.builder()
                .userId(entity.getUserId())
                .displayName(entity.getDisplayName())
                .type(entity.getType())
                .createdAt(entity.getCreatedAt())
                .build();
    }
}
