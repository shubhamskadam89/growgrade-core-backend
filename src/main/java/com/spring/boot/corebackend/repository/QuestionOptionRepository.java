package com.spring.boot.corebackend.repository;


import com.spring.boot.corebackend.entity.quiz.QuestionOption;
import com.spring.boot.corebackend.entity.quiz.QuizQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface QuestionOptionRepository
        extends JpaRepository<QuestionOption, UUID> {

    List<QuestionOption> findByQuestion(QuizQuestion question);
}
