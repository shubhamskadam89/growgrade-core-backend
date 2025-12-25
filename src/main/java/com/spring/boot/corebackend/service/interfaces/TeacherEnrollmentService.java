package com.spring.boot.corebackend.service.interfaces;

import com.spring.boot.corebackend.dtos.EnrollStudentRequest;
import com.spring.boot.corebackend.dtos.TeacherEnrollmentDto;
import com.spring.boot.corebackend.dtos.UserSummaryDto;

import java.util.UUID;

public interface TeacherEnrollmentService {
    TeacherEnrollmentDto enrollStudent(EnrollStudentRequest request);

    Iterable<UserSummaryDto> getEnrolledStudents(UUID teacherId);

    Iterable<UserSummaryDto> getStudentsTeachers(UUID studentsId);
}
