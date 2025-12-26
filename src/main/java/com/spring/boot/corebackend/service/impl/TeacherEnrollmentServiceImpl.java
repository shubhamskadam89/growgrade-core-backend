package com.spring.boot.corebackend.service.impl;

import com.spring.boot.corebackend.dtos.user.EnrollStudentRequest;
import com.spring.boot.corebackend.dtos.user.TeacherEnrollmentDto;
import com.spring.boot.corebackend.dtos.user.UserSummaryDto;
import com.spring.boot.corebackend.entity.user.TeacherEnrollment;
import com.spring.boot.corebackend.entity.user.UserProfile;
import com.spring.boot.corebackend.entity.user.UserType;
import com.spring.boot.corebackend.mapper.TeacherEnrollmentMapper;
import com.spring.boot.corebackend.mapper.UserProfileMapper;
import com.spring.boot.corebackend.repository.TeacherEnrollmentRepository;
import com.spring.boot.corebackend.repository.UserProfileRepository;
import com.spring.boot.corebackend.service.interfaces.TeacherEnrollmentService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class TeacherEnrollmentServiceImpl implements TeacherEnrollmentService {

    private  final TeacherEnrollmentRepository teacherEnrollmentRepository;
    private final UserProfileRepository userProfileRepository;
    @Override
    public TeacherEnrollmentDto enrollStudent(EnrollStudentRequest request) {
        if(!userProfileRepository.existsById(request.getTeacherId())){
            throw new EntityNotFoundException("No such teacher exist");
        }
        if(!userProfileRepository.existsById(request.getStudentId())){
            throw new EntityNotFoundException("No such student exist");
        }
        log.info("Enrolling student "+request.getStudentId()+" to teacher"+request.getTeacherId());
        UserProfile student = userProfileRepository.findById(request.getStudentId()).orElseThrow(() ->
                  new EntityNotFoundException("No Such student exist")
        );
        UserProfile teacher = userProfileRepository.findById(request.getTeacherId()).orElseThrow(() ->
        new EntityNotFoundException("No Such teacher exist"));
        boolean exists = teacherEnrollmentRepository.existsByTeacherAndStudent(teacher, student);
        if (exists) {
            throw new IllegalStateException("Student already enrolled with this teacher");
        }
        TeacherEnrollment enrollment = TeacherEnrollment.builder()
                .student(student)
                .teacher(teacher)
                .build();
        teacherEnrollmentRepository.save(enrollment);
        log.info("Student {} enrolled under teacher {}", request.toString());
        return TeacherEnrollmentMapper.toDto(enrollment);
    }

    @Override
    public Iterable<UserSummaryDto> getEnrolledStudents(UUID teacherId) {

        UserProfile teacherProfile = userProfileRepository.findById(teacherId).orElseThrow(()->
        new EntityNotFoundException("No such user profile exist"));

        if(!teacherProfile.getType().equals(UserType.TEACHER)){
            new EntityNotFoundException("No such user teacher exist");
        }

        List<TeacherEnrollment> enrolledStudents =
                teacherEnrollmentRepository.findByTeacher(teacherProfile);

        List<UserProfile> students = enrolledStudents.stream()
                .map(TeacherEnrollment::getStudent)
                .collect(Collectors.toList());

        List<UserSummaryDto> studentDto = students.stream().map(UserProfileMapper::toSummaryDto).toList();
        return studentDto ;
    }

    @Override
    public Iterable<UserSummaryDto> getStudentsTeachers(UUID studentId) {

        UserProfile studentProfile = userProfileRepository.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException("No such user profile exist"));

        if (!studentProfile.getType().equals(UserType.STUDENT)) {
            throw new EntityNotFoundException("User is not a student");
        }

        List<TeacherEnrollment> enrollments =
                teacherEnrollmentRepository.findByStudent(studentProfile);

        return enrollments.stream()
                .map(TeacherEnrollment::getTeacher)
                .map(UserProfileMapper::toSummaryDto)
                .toList();
    }

}
