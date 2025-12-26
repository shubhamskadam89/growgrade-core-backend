package com.spring.boot.corebackend.controllers;

import com.spring.boot.corebackend.dtos.user.EnrollStudentRequest;
import com.spring.boot.corebackend.dtos.user.TeacherEnrollmentDto;
import com.spring.boot.corebackend.dtos.user.UserSummaryDto;
import com.spring.boot.corebackend.service.impl.TeacherEnrollmentServiceImpl;
import com.spring.boot.corebackend.service.interfaces.TeacherEnrollmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/v1/enrollments")
@RequiredArgsConstructor
public class TeacherEnrollmentController {

    private final TeacherEnrollmentService enrollmentService;

    @PostMapping("/students")
    public ResponseEntity<TeacherEnrollmentDto> enrollStudent(
            @RequestBody EnrollStudentRequest request) {
        log.info("Request received to enroll student {} to teacher {}", request.getStudentId(), request.getTeacherId());
        TeacherEnrollmentDto enrollment = enrollmentService.enrollStudent(request);
        log.info("Student enrolled successfully.");
        return ResponseEntity.status(HttpStatus.CREATED).body(enrollment);
    }

    @GetMapping("/students/{teacherId}")
    public ResponseEntity<Iterable<UserSummaryDto>> getMyEnrolledStudents(@PathVariable UUID teacherId) {
        log.info("Request received to fetch enrolled students for teacher {}", teacherId);
        Iterable<UserSummaryDto> students = enrollmentService.getEnrolledStudents(teacherId);
        log.info("Successfully fetched enrolled students for teacher {}", teacherId);
        return ResponseEntity.status(HttpStatus.CREATED).body(students);
    }

    @GetMapping("/teachers/{studentId}")
    public ResponseEntity<Iterable<UserSummaryDto>> getMyTeachers(@PathVariable UUID studentId) {
        log.info("Request received to fetch teachers for student {}", studentId);
        Iterable<UserSummaryDto> teachers = enrollmentService.getStudentsTeachers(studentId);
        log.info("Successfully fetched teachers for student {}", studentId);
        return ResponseEntity.status(HttpStatus.CREATED).body(teachers);
    }
}
