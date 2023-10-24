package com.example.websocket_chat.login.member.exception;


import com.example.websocket_chat.global.exception.UnauthorizedException;

import static com.example.websocket_chat.global.error.ErrorStaticField.INVALID_REFRESH_TOKEN;

public class InvalidRefreshTokenException extends UnauthorizedException {
    private static final String MESSAGE = INVALID_REFRESH_TOKEN;
    public InvalidRefreshTokenException() {
        super(MESSAGE);
    }
}
