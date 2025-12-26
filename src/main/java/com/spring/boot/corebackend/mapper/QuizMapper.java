package com.spring.boot.corebackend.mapper;

import com.spring.boot.corebackend.dtos.quiz.QuestionDto;
import com.spring.boot.corebackend.dtos.quiz.QuestionOptionViewDto;
import com.spring.boot.corebackend.dtos.quiz.QuizDetailDto;
import com.spring.boot.corebackend.dtos.quiz.QuizSummaryDto;
import com.spring.boot.corebackend.entity.quiz.Quiz;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

// NOTE: Service layer must ensure required associations are eagerly fetched.
// Mappers must never trigger lazy loading.
public final class QuizMapper {

    private QuizMapper() {
    }

    public static QuizSummaryDto toSummaryDto(Quiz entity) {
        if (entity == null)
            return null;
        return QuizSummaryDto.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .createdBy(UserProfileMapper.toSummaryDto(entity.getCreatedBy()))
                .visibility(entity.getVisibility())
                .level(entity.getLevel())
                .timeLimitSeconds(entity.getTimeLimitSeconds())
                .totalScore(entity.getTotalScore())
                .createdAt(entity.getCreatedAt())
                .scheduledAt(entity.getScheduledAt())
                .build();
    }

    public static List<QuizSummaryDto> toSummaryDtos(java.util.Collection<Quiz> quizzes) {
        if (quizzes == null)
            return Collections.emptyList();
        return quizzes.stream()
                .map(QuizMapper::toSummaryDto)
                .toList();
    }

    public static QuizDetailDto toDetailDto(Quiz entity) {
        if (entity == null)
            return null;
        return QuizDetailDto.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .createdBy(UserProfileMapper.toSummaryDto(entity.getCreatedBy()))
                .visibility(entity.getVisibility())
                .level(entity.getLevel())
                .timeLimitSeconds(entity.getTimeLimitSeconds())
                .totalScore(entity.getTotalScore())
                .createdAt(entity.getCreatedAt())
                .scheduledAt(entity.getScheduledAt())
                .questions(entity.getQuestions() != null
                        ? entity.getQuestions().stream()
                                .map(QuizQuestionMapper::toDto)
                                .toList()
                        : Collections.emptyList())
                .build();
    }

    public static QuizDetailDto toPlayableDto(Quiz entity) {
        if (entity == null)
            return null;

        List<QuestionDto> questions = entity.getQuestions() != null
                ? entity.getQuestions().stream()
                        .map(q -> {
                            QuestionDto original = QuizQuestionMapper.toDto(q);
                            if (original == null)
                                return null;

                            // Create modifiable copy of options to shuffle (Immutability pattern: Create
                            // New DTO)
                            List<QuestionOptionViewDto> shuffledOptions = original.getOptions() != null
                                    ? new ArrayList<>(original.getOptions())
                                    : new ArrayList<>();

                            Collections.shuffle(shuffledOptions);

                            return QuestionDto.builder()
                                    .id(original.getId())
                                    .text(original.getText())
                                    .difficulty(original.getDifficulty())
                                    .options(shuffledOptions)
                                    .build();
                        })
                        .collect(Collectors.toList()) // Mutable list needed for shuffling below
                : new ArrayList<>();

        // Shuffle questions
        Collections.shuffle(questions);

        return QuizDetailDto.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .createdBy(UserProfileMapper.toSummaryDto(entity.getCreatedBy()))
                .visibility(entity.getVisibility())
                .level(entity.getLevel())
                .timeLimitSeconds(entity.getTimeLimitSeconds())
                .totalScore(entity.getTotalScore())
                .createdAt(entity.getCreatedAt())
                .scheduledAt(entity.getScheduledAt())
                .questions(questions)
                .build();
    }
}
