package com.spring.boot.corebackend.service.impl;

import com.spring.boot.corebackend.dtos.quiz.QuizCreationRequest;
import com.spring.boot.corebackend.dtos.quiz.QuizDetailDto;
import com.spring.boot.corebackend.dtos.quiz.QuizSummaryDto;
import com.spring.boot.corebackend.entity.quiz.Quiz;
import com.spring.boot.corebackend.entity.quiz.QuizQuestion;
import com.spring.boot.corebackend.entity.quiz.QuizType;
import com.spring.boot.corebackend.entity.user.UserProfile;
import com.spring.boot.corebackend.mapper.QuizMapper;
import com.spring.boot.corebackend.mapper.QuizQuestionMapper;
import com.spring.boot.corebackend.repository.QuizQuestionRepository;
import com.spring.boot.corebackend.repository.QuizRepository;
import com.spring.boot.corebackend.repository.UserProfileRepository;
import com.spring.boot.corebackend.service.interfaces.QuizService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService {

    private final QuizRepository quizRepository;
    private final UserProfileRepository userProfileRepository;
    private final QuizQuestionRepository quizQuestionRepository;

    @Override
    @Transactional
    public QuizSummaryDto createQuiz(QuizCreationRequest request, UUID userId) {
        if (request.getTitle() == null || request.getTitle().isBlank()) {
            throw new IllegalArgumentException("No title to quiz provided");
        }

        UserProfile creator = userProfileRepository
                .findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found: " + userId));

        Quiz quiz = Quiz.builder()
                .title(request.getTitle())
                .quizType(QuizType.PERSONAL)
                .createdBy(creator)
                .visibility(request.getVisibility())
                .level(request.getLevel())
                .timeLimitSeconds(request.getTimeLimitSeconds())
                .totalScore(request.getTotalScore())
                .scheduledAt(request.getScheduledAt())
                .build();

        if (request.getQuestions() != null) {
            List<QuizQuestion> questions = request.getQuestions().stream()
                    .map(q -> QuizQuestionMapper.toEntity(q, quiz))
                    .toList();
            quiz.setQuestions(questions);
        }

        Quiz saved = quizRepository.save(quiz);
        return QuizMapper.toSummaryDto(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public QuizDetailDto getQuizDetail(UUID quizId) {
        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new EntityNotFoundException("Quiz not found: " + quizId));
        List<QuizQuestion> questions = quizQuestionRepository.findByQuiz(quiz);
        quiz.setQuestions(questions);
        // Initialize lazy createdBy
        if (quiz.getCreatedBy() != null) {
            quiz.getCreatedBy().getDisplayName();
        }
        return QuizMapper.toDetailDto(quiz);
    }

    @Override
    @Transactional(readOnly = true)
    public QuizDetailDto getPlayableQuiz(UUID quizId) {
        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new EntityNotFoundException("Quiz not found: " + quizId));
        List<QuizQuestion> questions = quizQuestionRepository.findByQuiz(quiz);
        quiz.setQuestions(questions);
        if (quiz.getCreatedBy() != null) {
            quiz.getCreatedBy().getDisplayName();
        }
        return QuizMapper.toPlayableDto(quiz);
    }

    @Override
    @Transactional(readOnly = true)
    public List<QuizSummaryDto> getMyQuizzes() {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || auth.getPrincipal() == null) {
            return Collections.emptyList();
        }
        UUID userId = (UUID) auth.getPrincipal();
        UserProfile creator = userProfileRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found: " + userId));
        List<Quiz> quizzes = quizRepository.findByCreatedBy(creator);
        // Initialize lazy createdBy for each quiz
        quizzes.forEach(q -> {
            if (q.getCreatedBy() != null) {
                q.getCreatedBy().getDisplayName();
            }
        });
        return QuizMapper.toSummaryDtos(quizzes);
    }

    @Override
    @Transactional
    public void deleteQuiz(UUID quizId) {
        if (!quizRepository.existsById(quizId)) {
            throw new EntityNotFoundException("Quiz not found: " + quizId);
        }
        quizRepository.deleteById(quizId);
    }
}
