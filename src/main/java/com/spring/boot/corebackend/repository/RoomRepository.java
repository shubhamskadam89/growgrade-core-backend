package com.spring.boot.corebackend.repository;
import com.spring.boot.corebackend.entity.Room;
import com.spring.boot.corebackend.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface RoomRepository extends JpaRepository<Room, UUID> {

    List<Room> findByCreatedBy(UserProfile creator);

    List<Room> findByActiveTrue();
}