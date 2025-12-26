package com.spring.boot.corebackend.service.interfaces;

import com.spring.boot.corebackend.dtos.quiz.QuizSummaryDto;
import com.spring.boot.corebackend.dtos.user.UserSummaryDto;

public interface AdminService {

    Iterable<UserSummaryDto> getAllUsers();
    Iterable<QuizSummaryDto> getAllQuizzes();



}
