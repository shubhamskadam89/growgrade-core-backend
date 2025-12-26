package com.spring.boot.corebackend.config.security;

import org.springframework.stereotype.Service;

@Service
public class JwtService {

    public String extractUserId(String token) {
        // TODO: Implement actual JWT extraction logic
        return token; // Placeholder
    }
}
