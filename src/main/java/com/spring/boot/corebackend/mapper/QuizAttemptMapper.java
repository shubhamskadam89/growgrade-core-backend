package com.spring.boot.corebackend.mapper;

import com.spring.boot.corebackend.dtos.QuizAttemptResultDto;
import com.spring.boot.corebackend.entity.QuizAttempt;

public final class QuizAttemptMapper {

    private QuizAttemptMapper() {
    }

    public static QuizAttemptResultDto toResultDto(QuizAttempt entity) {
        if (entity == null)
            return null;
        return QuizAttemptResultDto.builder()
                .id(entity.getId())
                .quiz(QuizMapper.toSummaryDto(entity.getQuiz())) // Use summary to avoid recursion/heavy load
                .user(UserProfileMapper.toSummaryDto(entity.getUser()))
                .score(entity.getScore())
                .timeTakenSeconds(entity.getTimeTakenSeconds())
                .startedAt(entity.getStartedAt())
                .completedAt(entity.getCompletedAt())
                .build();
    }

    public static java.util.List<QuizAttemptResultDto> toResultDtos(java.util.Collection<QuizAttempt> entities) {
        if (entities == null)
            return java.util.Collections.emptyList();
        return entities.stream()
                .map(QuizAttemptMapper::toResultDto)
                .collect(java.util.stream.Collectors.toList());
    }
}
