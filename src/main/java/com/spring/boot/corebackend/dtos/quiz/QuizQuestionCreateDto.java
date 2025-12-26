package com.spring.boot.corebackend.dtos.quiz;

import com.spring.boot.corebackend.entity.quiz.Difficulty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class QuizQuestionCreateDto {
    private String text;
    private Difficulty difficulty;
    private List<QuestionOptionCreateDto> options;
}
