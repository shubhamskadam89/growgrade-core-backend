package com.spring.boot.corebackend.dtos.quiz;

import com.spring.boot.corebackend.entity.quiz.Difficulty;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Builder
public class QuestionDto {
    private UUID id;
    private String text;
    private Difficulty difficulty;
    private List<QuestionOptionViewDto> options;
}
