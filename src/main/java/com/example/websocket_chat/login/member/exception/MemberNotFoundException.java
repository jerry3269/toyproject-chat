package com.example.websocket_chat.login.member.exception;


import com.example.websocket_chat.global.exception.NotFoundException;

import static com.example.websocket_chat.global.error.ErrorStaticField.USER_NOT_FOUND;

public class MemberNotFoundException extends NotFoundException {

    private static final String MESSAGE = USER_NOT_FOUND;
    public MemberNotFoundException() {
        super(MESSAGE);
    }
}
