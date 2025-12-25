package com.spring.boot.corebackend.entity;


public enum RoomStatus {
    CREATED,        // room created, not yet joinable
    LOBBY_OPEN,     // users can join
    IN_PROGRESS,    // quiz running
    ENDED           // finished or force closed
}
