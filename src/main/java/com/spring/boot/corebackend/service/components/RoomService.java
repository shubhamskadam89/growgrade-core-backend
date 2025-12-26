package com.spring.boot.corebackend.service.components;

import com.spring.boot.corebackend.entity.quiz.Quiz;
import com.spring.boot.corebackend.entity.room.Room;
import com.spring.boot.corebackend.entity.room.RoomStatus;
import com.spring.boot.corebackend.entity.user.UserProfile;
import com.spring.boot.corebackend.repository.QuizRepository;
import com.spring.boot.corebackend.repository.RoomRepository;
import com.spring.boot.corebackend.repository.UserProfileRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;
    private final QuizRepository quizRepository;

    private static final SecureRandom RANDOM = new SecureRandom();
    private final UserProfileRepository userProfileRepository;

    public Room createRoom(UUID quizId, UUID userId) {

        var quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new IllegalArgumentException("Quiz not found"));

        UserProfile userProfile = userProfileRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found: " + quizId));
        Room room = Room.builder()
                .quiz(quiz)
                .createdBy(userProfile)
                .createdAt(Instant.now())
                .startedAt(Instant.now())
                .endedAt(Instant.now().plusSeconds(3600))
                .roomPin(generatePin())
                .status(RoomStatus.LOBBY_OPEN)
                .build();

        return roomRepository.save(room);
    }

    private String generatePin() {
        // 6-digit numeric PIN (Kahoot style)
        int pin = 100000 + RANDOM.nextInt(900000);
        return String.valueOf(pin);
    }
}
