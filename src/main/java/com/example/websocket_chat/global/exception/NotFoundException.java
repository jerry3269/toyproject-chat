package com.example.websocket_chat.global.exception;

import lombok.Getter;

import static com.example.websocket_chat.global.error.ErrorStaticField.NOT_FOUND;


@Getter
public abstract class NotFoundException extends RuntimeException{
    private static final int STATUS_CODE = NOT_FOUND;
    private String message;
    public NotFoundException(String message) {
        this.message = message;
    }
}
