package com.example.websocket_chat.global.exception;

import lombok.Getter;

import static com.example.websocket_chat.global.error.ErrorStaticField.FORBIDDEN;


@Getter
public abstract class ForbiddenException extends RuntimeException{
    private static final int STATUS_CODE = FORBIDDEN;
    private String message;

    public ForbiddenException(String message) {
        this.message = message;
    }
}
