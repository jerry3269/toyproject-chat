package com.example.websocket_chat.chat.controller;

import com.example.websocket_chat.chat.domain.ChatRoom;
import com.example.websocket_chat.chat.dto.RoomCreateForm;
import com.example.websocket_chat.chat.service.ChatRoomService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/chat")
@RestController
public class ChatController {
    private final ChatRoomService chatRoomService;

    @PostMapping
    public ChatRoom createRoom(@RequestBody @Valid RoomCreateForm roomCreateForm) {
        return chatRoomService.createRoom(roomCreateForm.name());
    }

    @GetMapping
    public List<ChatRoom> findAllRoom() {
        return chatRoomService.findAllRoom();
    }
}
