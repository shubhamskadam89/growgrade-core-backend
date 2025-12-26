package com.spring.boot.corebackend.dtos.user;

import com.spring.boot.corebackend.entity.user.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class UserSummaryDto {
    private UUID userId;
    private String displayName;
    private UserType type;
}
