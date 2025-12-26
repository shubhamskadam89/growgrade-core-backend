package com.spring.boot.corebackend.mapper;

import com.spring.boot.corebackend.dtos.quiz.QuestionOptionViewDto;
import com.spring.boot.corebackend.entity.quiz.QuestionOption;

public final class QuestionOptionMapper {

    private QuestionOptionMapper() {
    }

    public static QuestionOptionViewDto toViewDto(QuestionOption entity) {
        if (entity == null)
            return null;
        // SECURITY: Never map 'correct' flag to ViewDto
        return QuestionOptionViewDto.builder()
                .id(entity.getId())
                .optionText(entity.getOptionText())
                .build();
    }
}
