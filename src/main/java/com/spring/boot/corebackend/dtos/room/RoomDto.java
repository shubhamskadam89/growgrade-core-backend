package com.spring.boot.corebackend.dtos.room;
import com.spring.boot.corebackend.dtos.quiz.QuizSummaryDto;
import com.spring.boot.corebackend.dtos.user.UserSummaryDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class RoomDto {
    private UUID id;
    private QuizSummaryDto quiz;
    private UserSummaryDto createdBy;
    private boolean active;
    private Instant startedAt;
}
