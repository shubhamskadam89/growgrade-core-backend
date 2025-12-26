package com.spring.boot.corebackend.config.websocket;

import com.spring.boot.corebackend.config.security.JwtService;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.security.Principal;
import java.util.Map;

@Component
public class WebSocketPrincipalConfig extends DefaultHandshakeHandler {

    private final JwtService jwtService;

    public WebSocketPrincipalConfig(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected Principal determineUser(
            ServerHttpRequest request,
            WebSocketHandler wsHandler,
            Map<String, Object> attributes) {
        String token = (String) attributes.get("jwt");
        if (token == null)
            return null;

        String userId = jwtService.extractUserId(token);

        return () -> userId; // Principal.getName()
    }
}
