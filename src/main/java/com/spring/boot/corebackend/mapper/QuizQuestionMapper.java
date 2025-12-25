package com.spring.boot.corebackend.mapper;

import com.spring.boot.corebackend.dtos.QuestionDto;
import com.spring.boot.corebackend.entity.QuizQuestion;

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
}
