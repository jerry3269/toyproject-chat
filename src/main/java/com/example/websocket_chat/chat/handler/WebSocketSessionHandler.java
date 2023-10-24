package com.example.websocket_chat.chat.handler;

import com.example.websocket_chat.chat.exception.RoomNotFountException;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class WebSocketSessionHandler {
    private ConcurrentHashMap<String, Set<WebSocketSession>> chatRooms = new ConcurrentHashMap<>();

    public void addSession(String roomId, WebSocketSession session) {
        chatRooms.computeIfAbsent(roomId, key -> new HashSet<>()).add(session);
    }

    public void removeSession(String roomId, WebSocketSession session) {
        Set<WebSocketSession> sessions = chatRooms.get(roomId);
        if (sessions != null) {
            sessions.remove(session);
        }
    }

    public Set<WebSocketSession> getSessionsInRoom(String roomId) {
        return Optional.ofNullable(chatRooms.get(roomId)).orElseThrow(RoomNotFountException::new);
    }
}
