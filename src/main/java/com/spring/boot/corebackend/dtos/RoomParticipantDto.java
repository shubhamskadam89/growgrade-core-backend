package com.spring.boot.corebackend.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoomParticipantDto {
    private Long id;
    private UserSummaryDto user;
}
