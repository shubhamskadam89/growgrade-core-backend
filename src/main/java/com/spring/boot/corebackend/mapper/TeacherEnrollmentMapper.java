package com.spring.boot.corebackend.mapper;

import com.spring.boot.corebackend.dtos.TeacherEnrollmentDto;
import com.spring.boot.corebackend.entity.TeacherEnrollment;

public final class TeacherEnrollmentMapper {

    private TeacherEnrollmentMapper() {
    }

    public static TeacherEnrollmentDto toDto(TeacherEnrollment entity) {
        if (entity == null)
            return null;
        return TeacherEnrollmentDto.builder()
                .id(entity.getId())
                .teacher(UserProfileMapper.toSummaryDto(entity.getTeacher()))
                .student(UserProfileMapper.toSummaryDto(entity.getStudent()))
                .build();
    }

    public static java.util.List<TeacherEnrollmentDto> toDtos(java.util.Collection<TeacherEnrollment> entities) {
        if (entities == null)
            return java.util.Collections.emptyList();
        return entities.stream()
                .map(TeacherEnrollmentMapper::toDto)
                .collect(java.util.stream.Collectors.toList());
    }
}
