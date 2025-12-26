package com.spring.boot.corebackend.entity.quiz;
import com.spring.boot.corebackend.entity.user.UserProfile;

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
        @Index(name = "idx_quiz_visibility", columnList = "visibility"),
        @Index(name = "idx_quiz_type", columnList = "quiz_type")
})
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String title;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "created_by_user_id", nullable = false)
    private UserProfile createdBy;

    /**
     * PERSONAL = self practice (AI generated)
     * SHARED = friends / enrolled
     * LIVE = used in rooms
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private QuizType quizType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private QuizVisibility visibility;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private QuizLevel level;

    /**
     * Per-question time limit (Kahoot style)
     */
    private Integer timeLimitSeconds;

    private Integer totalScore;

    /**
     * If quiz is AI-generated from PDF/PPT
     */
    private boolean aiGenerated;

    /**
     * Source document reference (handled by AI team)
     */
    private String sourceDocumentRef;

    private Instant scheduledAt;

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private java.util.List<QuizQuestion> questions = new java.util.ArrayList<>();

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    // JPA identity safety
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Quiz))
            return false;
        Quiz other = (Quiz) o;
        return id != null && id.equals(other.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
