package com.spring.boot.corebackend.dtos.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class TeacherEnrollmentDto {
    private UUID id;
    private UserSummaryDto teacher;
    private UserSummaryDto student;
}
