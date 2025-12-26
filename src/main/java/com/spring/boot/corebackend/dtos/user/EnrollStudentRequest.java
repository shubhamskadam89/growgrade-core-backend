package com.spring.boot.corebackend.dtos.user;

import lombok.*;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EnrollStudentRequest {

    private UUID teacherId;
    private UUID studentId;
}
