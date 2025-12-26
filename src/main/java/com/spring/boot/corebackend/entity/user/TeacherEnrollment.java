package com.spring.boot.corebackend.entity.user;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "teacher_enrollments", indexes = {
        @Index(name = "idx_teacher_enrollment_teacher_id", columnList = "teacher_user_id"),
        @Index(name = "idx_teacher_enrollment_student_id", columnList = "student_user_id")
})
public class TeacherEnrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_user_id", nullable = false)
    private UserProfile teacher;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_user_id", nullable = false)
    private UserProfile student;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        TeacherEnrollment that = (TeacherEnrollment) o;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}