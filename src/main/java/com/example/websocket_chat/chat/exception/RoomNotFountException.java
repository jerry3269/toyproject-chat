package com.example.websocket_chat.chat.exception;

import com.example.websocket_chat.global.exception.NotFoundException;

import static com.example.websocket_chat.global.error.ErrorStaticField.ROOM_NOT_FOUND;

public class RoomNotFountException extends NotFoundException {
    private static final String MESSAGE = ROOM_NOT_FOUND;
    public RoomNotFountException() {
        super(MESSAGE);
    }
}
