package com.spring.boot.corebackend.service.interfaces;

import com.spring.boot.corebackend.dtos.CreateUserProfileDto;
import com.spring.boot.corebackend.dtos.UserProfileDto;

public interface UserProfileService {

    UserProfileDto getUserProfile(java.util.UUID userId);

    UserProfileDto createUserProfile(CreateUserProfileDto dto, java.util.UUID userId);
}
