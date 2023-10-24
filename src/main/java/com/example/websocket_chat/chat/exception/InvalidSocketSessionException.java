package com.example.websocket_chat.chat.exception;

import com.example.websocket_chat.global.exception.UnauthorizedException;

import static com.example.websocket_chat.global.error.ErrorStaticField.INVALID_SOCKET_SESSION;

public class InvalidSocketSessionException extends UnauthorizedException {

    private static final String MESSAGE = INVALID_SOCKET_SESSION;
    public InvalidSocketSessionException() {
        super(MESSAGE);
    }
}
