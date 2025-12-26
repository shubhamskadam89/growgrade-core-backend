package com.spring.boot.corebackend.dtos.user;

import com.spring.boot.corebackend.entity.user.UserType;
import lombok.*;

import java.util.UUID;


@Getter
@Setter
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserProfileDto {
    private UUID uuid;
    private String displayName;
    private UserType type;
}
