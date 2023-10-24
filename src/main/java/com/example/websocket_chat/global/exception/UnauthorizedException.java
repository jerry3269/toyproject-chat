package com.example.websocket_chat.global.exception;

import lombok.Getter;

import static com.example.websocket_chat.global.error.ErrorStaticField.UNAUTHORIZED;


@Getter
public abstract class UnauthorizedException extends RuntimeException{
    private static final int STATUS_CODE = UNAUTHORIZED;
    private String message;

    public UnauthorizedException(String message) {
        this.message = message;
    }

}
