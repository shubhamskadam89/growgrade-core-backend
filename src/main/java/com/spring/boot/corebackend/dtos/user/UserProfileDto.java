package com.spring.boot.corebackend.dtos.user;

import com.spring.boot.corebackend.entity.user.UserType;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
@Builder
public class UserProfileDto {
    private UUID userId;
    private String displayName;
    private UserType type;
    private Instant createdAt;
}
