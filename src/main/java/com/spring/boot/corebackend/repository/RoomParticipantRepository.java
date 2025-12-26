package com.spring.boot.corebackend.repository;

import com.spring.boot.corebackend.entity.room.Room;
import com.spring.boot.corebackend.entity.room.RoomParticipant;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomParticipantRepository
        extends JpaRepository<RoomParticipant, Long> {

    @EntityGraph(attributePaths = { "room", "user" })
    List<RoomParticipant> findByRoom(Room room);

}