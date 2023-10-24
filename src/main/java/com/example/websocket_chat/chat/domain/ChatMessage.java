package com.example.websocket_chat.chat.domain;

import com.example.websocket_chat.chat.domain.constant.MessageType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private MessageType messageType;
    private String roomId;
    private String sender;
    private String message;

    private ChatMessage(MessageType messageType, String roomId, String sender, String message) {
        this.messageType = messageType;
        this.roomId = roomId;
        this.sender = sender;
        this.message = message;
    }

    private static ChatMessage of(MessageType messageType, String roomId, String sender, String message) {
        return new ChatMessage(messageType, roomId, sender, message);
    }
}
