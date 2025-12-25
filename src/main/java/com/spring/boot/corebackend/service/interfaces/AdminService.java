package com.spring.boot.corebackend.service.interfaces;

import com.spring.boot.corebackend.dtos.QuizSummaryDto;
import com.spring.boot.corebackend.dtos.UserSummaryDto;

public interface AdminService {

    Iterable<UserSummaryDto> getAllUsers();
    Iterable<QuizSummaryDto> getAllQuizzes();



}
