package com.spring.boot.corebackend.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/rooms")
public class RoomController {

//    @PostMapping
//    public RoomDto createRoom(
//            @RequestBody CreateRoomDto request
//    );
//
//    @PostMapping("/{roomId}/join")
//    public void joinRoom(
//            @PathVariable UUID roomId
//    );
//
//    @PostMapping("/{roomId}/leave")
//    public void leaveRoom(
//            @PathVariable UUID roomId
//    );
//
//    @GetMapping("/{roomId}")
//    public RoomDto getRoom(
//            @PathVariable UUID roomId
//    );
//
//    @GetMapping("/{roomId}/participants")
//    public List<RoomParticipantDto> getRoomParticipants(
//            @PathVariable UUID roomId
//    );
}
