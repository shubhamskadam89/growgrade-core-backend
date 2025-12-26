package com.spring.boot.corebackend.dtos.quiz;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class QuestionOptionViewDto {
    private UUID id;
    private String optionText;
}
