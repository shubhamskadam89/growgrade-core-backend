package com.spring.boot.corebackend.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import lombok.*;

import java.time.Instant;
import java.util.UUID;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(
        name = "quiz_attempts",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_quiz_user_attempt",
                        columnNames = {"quiz_id", "user_user_id"}
                )
        },
        indexes = {
                @Index(name = "idx_quiz_attempt_quiz_id", columnList = "quiz_id"),
                @Index(name = "idx_quiz_attempt_user_id", columnList = "user_user_id")
        }
)
public class QuizAttempt {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "quiz_id", nullable = false)
    private Quiz quiz;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_user_id", nullable = false)
    private UserProfile user;

    private Integer score;
    private Integer timeTakenSeconds;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Instant startedAt;

    private Instant completedAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof QuizAttempt)) return false;
        QuizAttempt other = (QuizAttempt) o;
        return id != null && id.equals(other.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
