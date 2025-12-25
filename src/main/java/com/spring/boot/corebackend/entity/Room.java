package com.spring.boot.corebackend.entity;

import com.spring.boot.corebackend.entity.RoomStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(
        name = "rooms",
        indexes = {
                @Index(name = "idx_room_quiz_id", columnList = "quiz_id"),
                @Index(name = "idx_room_created_by", columnList = "created_by_user_id"),
                @Index(name = "idx_room_status", columnList = "status"),
                @Index(name = "idx_room_pin", columnList = "room_pin")
        }
)
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    /**
     * Human-readable room PIN (like Kahoot: 6–8 chars)
     * Unique only while room is active (enforced at service level)
     */
    @Column(name = "room_pin", nullable = false, length = 10)
    private String roomPin;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "quiz_id", nullable = false)
    private Quiz quiz;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "created_by_user_id", nullable = false)
    private UserProfile createdBy;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private RoomStatus status;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    private Instant startedAt;

    private Instant endedAt;

    /**
     * Participants in this room.
     * Never expose directly in API responses.
     */
    @OneToMany(
            mappedBy = "room",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<RoomParticipant> participants = new HashSet<>();

    // -----------------------------
    // JPA identity safety
    // -----------------------------
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Room)) return false;
        Room other = (Room) o;
        return id != null && id.equals(other.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
