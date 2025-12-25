package com.spring.boot.corebackend.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class QuestionOptionCreateDto {
    private String optionText;
    private boolean correct;
}
