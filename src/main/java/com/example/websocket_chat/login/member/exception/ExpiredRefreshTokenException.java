package com.example.websocket_chat.login.member.exception;


import com.example.websocket_chat.global.exception.UnauthorizedException;

import static com.example.websocket_chat.global.error.ErrorStaticField.EXPIRED_REFRESH_TOKEN;

public class ExpiredRefreshTokenException extends UnauthorizedException {
    private static final String MESSAGE = EXPIRED_REFRESH_TOKEN;
    public ExpiredRefreshTokenException() {
        super(MESSAGE);
    }
}
