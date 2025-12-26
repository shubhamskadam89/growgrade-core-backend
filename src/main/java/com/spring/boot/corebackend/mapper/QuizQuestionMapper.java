package com.spring.boot.corebackend.mapper;

import com.spring.boot.corebackend.dtos.quiz.QuestionDto;
import com.spring.boot.corebackend.dtos.quiz.QuizQuestionCreateDto;
import com.spring.boot.corebackend.entity.quiz.QuestionOption;
import com.spring.boot.corebackend.entity.quiz.Quiz;
import com.spring.boot.corebackend.entity.quiz.QuizQuestion;

import java.util.Collections;
import java.util.stream.Collectors;

public final class QuizQuestionMapper {

    private QuizQuestionMapper() {
    }

    public static QuestionDto toDto(QuizQuestion entity) {
        if (entity == null)
            return null;
        return QuestionDto.builder()
                .id(entity.getId())
                .text(entity.getQuestionText())
                .difficulty(entity.getDifficulty())
                .options(entity.getOptions() != null
                        ? entity.getOptions().stream()
                                .map(QuestionOptionMapper::toViewDto)
                                .collect(Collectors.toList())
                        : Collections.emptyList())
                .build();
    }

    public static QuizQuestion toEntity(QuizQuestionCreateDto dto, Quiz quiz) {
        if (dto == null)
            return null;

        QuizQuestion question = QuizQuestion.builder()
                .quiz(quiz)
                .questionText(dto.getText())
                .difficulty(dto.getDifficulty())
                .build();

        if (dto.getOptions() != null) {
            var options = dto.getOptions().stream()
                    .map(opt -> QuestionOption.builder()
                            .question(question)
                            .optionText(opt.getOptionText())
                            .correct(opt.isCorrect())
                            .build())
                    .collect(Collectors.toList());
            question.setOptions(options);
        }

        return question;
    }
}
