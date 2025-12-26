package com.spring.boot.corebackend.service.interfaces;

import com.spring.boot.corebackend.dtos.user.EnrollStudentRequest;
import com.spring.boot.corebackend.dtos.user.TeacherEnrollmentDto;
import com.spring.boot.corebackend.dtos.user.UserSummaryDto;

import java.util.UUID;

public interface TeacherEnrollmentService {
    TeacherEnrollmentDto enrollStudent(EnrollStudentRequest request);

    Iterable<UserSummaryDto> getEnrolledStudents(UUID teacherId);

    Iterable<UserSummaryDto> getStudentsTeachers(UUID studentsId);
}
