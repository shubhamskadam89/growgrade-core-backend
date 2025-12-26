package com.spring.boot.corebackend.repository;

import com.spring.boot.corebackend.entity.quiz.Quiz;
import com.spring.boot.corebackend.entity.user.UserProfile;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface QuizRepository extends JpaRepository<Quiz, UUID> {

    @EntityGraph(attributePaths = {"createdBy"})
    List<Quiz> findByCreatedBy(UserProfile creator);
    Quiz findByTitle(String title);

    List<Quiz> findByVisibility(String visibility);

    @EntityGraph(attributePaths = {"createdBy"})
    java.util.Optional<Quiz> findById(UUID id);
}