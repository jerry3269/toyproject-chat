package com.example.websocket_chat.chat.service;

import com.example.websocket_chat.chat.domain.ChatMessage;
import com.example.websocket_chat.chat.exception.InvalidSocketSessionException;
import com.example.websocket_chat.chat.handler.WebSocketSessionHandler;
import com.example.websocket_chat.chat.repository.ChatMessageRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.Set;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ChatMessageService {

    private final WebSocketSessionHandler webSocketSessionHandler;
    private final ChatMessageRepository chatMessageRepository;
    private final ObjectMapper mapper;

    @Transactional
    public void handlerAction(WebSocketSession session, ChatMessage chatMessage) {
        if (session.isOpen()) {
            if (chatMessage.isEnter()) {
                webSocketSessionHandler.addSession(chatMessage.getRoomId(), session);
                chatMessage.setMessage(chatMessage.getSender() + "님이 입장했습니다.");
            } else if (chatMessage.isQuit()) {
                webSocketSessionHandler.removeSession(chatMessage.getRoomId(), session);
                chatMessage.setMessage(chatMessage.getSender() + "님이 퇴장했습니다.");

                try{
                    session.close(CloseStatus.NORMAL);
                } catch (IOException e) {
                    log.error(e.getMessage(), e);
                }
            }
        } else {
            throw new InvalidSocketSessionException();
        }

        Set<WebSocketSession> sessions = webSocketSessionHandler.getSessionsInRoom(chatMessage.getRoomId());
        this.sendMessageToAll(sessions, chatMessage);
        chatMessageRepository.save(chatMessage);
    }

    private <T> void sendMessageToAll(Set<WebSocketSession> webSocketSessions, T message) {
        webSocketSessions.parallelStream()
                .forEach(session -> this.sendMessageToOne(session, message));
    }

    private <T> void sendMessageToOne(WebSocketSession session, T message) {
        try{
            session.sendMessage(new TextMessage(mapper.writeValueAsString(message)));
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }
}
