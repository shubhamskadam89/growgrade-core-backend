package com.spring.boot.corebackend.dtos.user;

import com.spring.boot.corebackend.entity.user.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class UserProfileDto {
    private UUID userId;
    private String displayName;
    private UserType type;
    private Instant createdAt;
}
