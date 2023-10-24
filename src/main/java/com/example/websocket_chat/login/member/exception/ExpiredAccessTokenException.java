package com.example.websocket_chat.login.member.exception;


import com.example.websocket_chat.global.exception.UnauthorizedException;

public class ExpiredAccessTokenException extends UnauthorizedException {
    public ExpiredAccessTokenException(String message) {
        super(message);
    }
}
