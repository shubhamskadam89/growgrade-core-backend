package com.spring.boot.corebackend.repository;

import com.spring.boot.corebackend.entity.user.TeacherEnrollment;
import com.spring.boot.corebackend.entity.user.UserProfile;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TeacherEnrollmentRepository
                extends JpaRepository<TeacherEnrollment, UUID> {

        boolean existsByTeacherAndStudent(
                        UserProfile teacher,
                        UserProfile student);

        @EntityGraph(attributePaths = { "teacher", "student" })
        List<TeacherEnrollment> findByTeacher(UserProfile teacher);
    @EntityGraph(attributePaths = { "teacher", "student" })
    List<TeacherEnrollment> findByStudent(UserProfile student);

}