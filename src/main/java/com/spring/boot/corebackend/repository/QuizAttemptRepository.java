package com.spring.boot.corebackend.repository;

import com.spring.boot.corebackend.entity.Quiz;
import com.spring.boot.corebackend.entity.QuizAttempt;
import com.spring.boot.corebackend.entity.UserProfile;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface QuizAttemptRepository
        extends JpaRepository<QuizAttempt, UUID> {

    @EntityGraph(attributePaths = { "quiz", "user" })
    List<QuizAttempt> findByUser(UserProfile user);

    @EntityGraph(attributePaths = { "quiz", "user" })
    List<QuizAttempt> findByQuiz(Quiz quiz);

    boolean existsByQuizAndUser(Quiz quiz, UserProfile user);
}