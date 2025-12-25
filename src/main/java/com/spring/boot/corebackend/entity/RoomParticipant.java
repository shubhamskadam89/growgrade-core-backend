package com.spring.boot.corebackend.entity;

import com.spring.boot.corebackend.entity.ParticipantRole;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(
        name = "room_participants",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_room_user",
                        columnNames = {"room_id", "user_user_id"}
                )
        },
        indexes = {
                @Index(name = "idx_room_participant_room_id", columnList = "room_id"),
                @Index(name = "idx_room_participant_user_id", columnList = "user_user_id")
        }
)
public class RoomParticipant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_user_id", nullable = false)
    private UserProfile user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private ParticipantRole role;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Instant joinedAt;

    private Instant leftAt;

    /**
     * Final score for this participant in this room
     * (never store score on User)
     */
    private Integer score;

    // -----------------------------
    // JPA identity safety
    // -----------------------------
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RoomParticipant)) return false;
        RoomParticipant other = (RoomParticipant) o;
        return id != null && id.equals(other.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
