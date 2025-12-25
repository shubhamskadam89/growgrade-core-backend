package com.spring.boot.corebackend.controllers;

import com.spring.boot.corebackend.dtos.EnrollStudentRequest;
import com.spring.boot.corebackend.dtos.TeacherEnrollmentDto;
import com.spring.boot.corebackend.dtos.UserSummaryDto;
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
            @RequestBody EnrollStudentRequest request
    ){
        log.info("Received Request to enroll student"+request.getStudentId()+ "to teacher"+request.getTeacherId());
        return ResponseEntity.status(HttpStatus.CREATED).body(enrollmentService.enrollStudent(request));
    }

    @GetMapping("/students/{teacherId}")
    public ResponseEntity<Iterable<UserSummaryDto>> getMyEnrolledStudents(@PathVariable UUID teacherId){
        log.info("Received Request to  fetch enrolled student to teacher"+teacherId);
        return ResponseEntity.status(HttpStatus.CREATED).body(enrollmentService.getEnrolledStudents(teacherId));
    }

    @GetMapping("/teachers/{studentId}")
    public ResponseEntity<Iterable<UserSummaryDto>> getMyTeachers(@PathVariable UUID studentId){
        log.info("Received Request to  fetch teachers to whom student is enrolled"+studentId);
        return ResponseEntity.status(HttpStatus.CREATED).body(enrollmentService.getStudentsTeachers(studentId));
    }
}
