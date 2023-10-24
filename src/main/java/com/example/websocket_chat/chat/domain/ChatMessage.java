package com.example.websocket_chat.chat.domain;

import com.example.websocket_chat.chat.domain.constant.MessageType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private MessageType messageType;
    private String roomId;
    private String sender;
    private String message;

    @Builder
    public ChatMessage(MessageType messageType, String roomId, String sender, String message) {
        this.messageType = messageType;
        this.roomId = roomId;
        this.sender = sender;
        this.message = message;
    }

    public Boolean isEnter() {
        return messageType.equals(MessageType.ENTER);
    }

    public Boolean isTalk() {
        return messageType.equals(MessageType.TALK);
    }

    public Boolean isQuit() {
        return messageType.equals(MessageType.QUIT);
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
