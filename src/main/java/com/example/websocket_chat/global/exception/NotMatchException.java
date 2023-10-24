package com.example.websocket_chat.global.exception;

import lombok.Getter;

import static com.example.websocket_chat.global.error.ErrorStaticField.NOT_MATCH;


@Getter
public abstract class NotMatchException extends RuntimeException {
    private static final int  STATUS_CODE = NOT_MATCH;

    private String message;

    public NotMatchException(String message) {
        this.message = message;
    }
}
