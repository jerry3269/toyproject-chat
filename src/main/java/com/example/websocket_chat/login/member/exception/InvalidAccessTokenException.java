package com.example.websocket_chat.login.member.exception;


import com.example.websocket_chat.global.exception.UnauthorizedException;

import static com.example.websocket_chat.global.error.ErrorStaticField.INVALID_ACCESS_TOKEN;

public class InvalidAccessTokenException extends UnauthorizedException {
    private static final String MESSAGE = INVALID_ACCESS_TOKEN;
    public InvalidAccessTokenException() {
        super(MESSAGE);
    }
}
