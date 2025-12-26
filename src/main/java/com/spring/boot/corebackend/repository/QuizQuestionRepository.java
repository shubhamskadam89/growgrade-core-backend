package com.spring.boot.corebackend.repository;

import com.spring.boot.corebackend.entity.quiz.Quiz;
import com.spring.boot.corebackend.entity.quiz.QuizQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface QuizQuestionRepository
        extends JpaRepository<QuizQuestion, UUID> {

    List<QuizQuestion> findByQuiz(Quiz quiz);
}