package com.example.websocket_chat.global.exception;

import lombok.Getter;

import static com.example.websocket_chat.global.error.ErrorStaticField.BAD_REQUEST;


@Getter
public abstract class DuplicationException extends RuntimeException {
    private static final int STATUS_CODE = BAD_REQUEST;
    private String message;
    public DuplicationException(String message) {
        this.message = message;
    }
}
