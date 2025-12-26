package com.spring.boot.corebackend.repository;

import com.spring.boot.corebackend.entity.quiz.Quiz;
import com.spring.boot.corebackend.entity.user.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface QuizRepository extends JpaRepository<Quiz, UUID> {

    List<Quiz> findByCreatedBy(UserProfile creator);

    List<Quiz> findByVisibility(String visibility);
}