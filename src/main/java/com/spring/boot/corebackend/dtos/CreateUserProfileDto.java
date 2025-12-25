package com.spring.boot.corebackend.dtos;

import com.spring.boot.corebackend.entity.UserType;
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
