package com.spring.boot.corebackend.config.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.UUID;

/**
 * Simple JWT authentication filter that extracts the user id (subject) from a Bearer token
 * and populates the SecurityContext with it as the authenticated principal.
 */
public class JwtAuthFilter extends OncePerRequestFilter {
    private final String secret;

    public JwtAuthFilter(String secret) {
        this.secret = secret;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain
    ) throws ServletException, IOException {

        String auth = request.getHeader("Authorization");
        if (auth != null && auth.startsWith("Bearer ")) {
            String token = auth.substring(7);
            try {
                var key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
                var claimsJws = Jwts.parser().verifyWith(key).build().parseSignedClaims(token);
                var claims = claimsJws.getPayload();

                String subject = claims.getSubject();
                if (subject != null) {
                    UUID userId = UUID.fromString(subject);
                    var authToken = new UsernamePasswordAuthenticationToken(
                            userId, null, Collections.emptyList());
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            } catch (Exception ex) {
                // Invalid token; clear context and proceed without authentication
                SecurityContextHolder.clearContext();
            }
        }
        chain.doFilter(request, response);
    }
}
