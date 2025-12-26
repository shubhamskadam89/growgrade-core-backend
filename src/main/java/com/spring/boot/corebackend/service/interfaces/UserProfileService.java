package com.spring.boot.corebackend.service.interfaces;

import com.spring.boot.corebackend.dtos.user.CreateUserProfileDto;
import com.spring.boot.corebackend.dtos.user.UserProfileDto;

public interface UserProfileService {

    UserProfileDto getUserProfile(java.util.UUID userId);

    UserProfileDto createUserProfile(CreateUserProfileDto dto, java.util.UUID userId);
}
