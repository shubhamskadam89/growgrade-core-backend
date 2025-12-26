package com.spring.boot.corebackend.dtos.user;

import com.spring.boot.corebackend.entity.user.UserType;
import lombok.*;


@Getter
@Setter
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserProfileDto {
    private String displayName;
    private UserType type;
}
