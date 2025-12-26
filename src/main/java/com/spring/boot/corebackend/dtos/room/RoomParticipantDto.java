package com.spring.boot.corebackend.dtos.room;
import com.spring.boot.corebackend.dtos.user.UserSummaryDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class RoomParticipantDto {
    private Long id;
    private UserSummaryDto user;
}
