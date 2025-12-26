package com.spring.boot.corebackend.dtos.quiz;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class QuestionOptionCreateDto {
    private String optionText;
    private boolean correct;
}
