package com.spring.boot.corebackend.dtos.room;
import com.spring.boot.corebackend.dtos.user.UserSummaryDto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoomParticipantDto {
    private Long id;
    private UserSummaryDto user;
}
