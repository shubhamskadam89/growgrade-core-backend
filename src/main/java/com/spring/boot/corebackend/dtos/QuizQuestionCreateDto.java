package com.spring.boot.corebackend.dtos;

import com.spring.boot.corebackend.entity.Difficulty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class QuizQuestionCreateDto {
    private String text;
    private Difficulty difficulty;
    private List<QuestionOptionCreateDto> options;
}
