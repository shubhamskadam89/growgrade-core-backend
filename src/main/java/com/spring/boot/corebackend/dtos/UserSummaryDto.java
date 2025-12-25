package com.spring.boot.corebackend.dtos;

import com.spring.boot.corebackend.entity.UserType;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class UserSummaryDto {
    private UUID userId;
    private String displayName;
    private UserType type;
}
