package com.example.websocket_chat.chat.handler;

import com.example.websocket_chat.chat.domain.ChatMessage;
import com.example.websocket_chat.chat.domain.ChatRoom;
import com.example.websocket_chat.chat.service.ChatRoomService;
import com.example.websocket_chat.chat.service.ChatMessageService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Slf4j
@RequiredArgsConstructor
@Component
public class WebSocketHandler extends TextWebSocketHandler {
    private final ObjectMapper mapper;
    private final ChatMessageService chatMessageService;
    private final ChatRoomService chatRoomService;

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        log.info("payload: {}", payload);
        log.info("session: {}", session.getId());
        ChatMessage chatMessage = mapper.readValue(payload, ChatMessage.class);

        ChatRoom chatRoom = chatRoomService.findRoomById(chatMessage.getRoomId());
        chatMessageService.handlerAction(session, chatMessage);
    }
}
