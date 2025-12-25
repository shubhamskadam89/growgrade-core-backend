package com.spring.boot.corebackend.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "quizzes", indexes = {
        @Index(name = "idx_quiz_created_by", columnList = "created_by_user_id"),
        @Index(name = "idx_quiz_visibility", columnList = "visibility")
})
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by_user_id", nullable = false)
    private UserProfile createdBy;

    @Enumerated(EnumType.STRING)
    private QuizVisibility visibility; // PRIVATE / FRIENDS / ENROLLED

    @Enumerated(EnumType.STRING)
    private QuizLevel level; // EASY / MEDIUM / HARD

    private Integer timeLimitSeconds;
    private Integer totalScore;

    private Instant scheduledAt; // null = instant

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private java.util.List<QuizQuestion> questions = new java.util.ArrayList<>();

    @CreationTimestamp
    private Instant createdAt;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Quiz quiz = (Quiz) o;
        return id != null && id.equals(quiz.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
