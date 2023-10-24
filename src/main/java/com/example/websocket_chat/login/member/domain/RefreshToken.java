package com.example.websocket_chat.login.member.domain;

public record RefreshToken(String refreshToken) {
    public static RefreshToken of(String refreshToken){
        return new RefreshToken(refreshToken);
    }
}
