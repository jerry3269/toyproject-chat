package com.example.websocket_chat.chat.service;

import com.example.websocket_chat.chat.domain.ChatRoom;
import com.example.websocket_chat.chat.exception.RoomNotFountException;
import com.example.websocket_chat.chat.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;

    public List<ChatRoom> findAllRoom() {
        return chatRoomRepository.findAll();
    }

    public ChatRoom findRoomById(String roomId) {
        return chatRoomRepository.findById(roomId).orElseThrow(RoomNotFountException::new);
    }

    @Transactional
    public ChatRoom createRoom(String name) {
        String randomId = UUID.randomUUID().toString();
        ChatRoom chatRoom = ChatRoom.builder()
                .roomId(randomId)
                .name(name)
                .build();
        return chatRoomRepository.save(chatRoom);
    }
}
