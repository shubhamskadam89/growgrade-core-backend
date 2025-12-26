package com.spring.boot.corebackend.dtos.room;


import java.util.UUID;

public record CreateRoomResponse(
        UUID roomId,
        String roomPin
) {}
