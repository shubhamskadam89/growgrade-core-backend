package com.spring.boot.corebackend.dtos.user;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class TeacherEnrollmentDto {
    private UUID id;
    private UserSummaryDto teacher;
    private UserSummaryDto student;
}
