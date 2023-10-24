package com.example.websocket_chat.login.member.exception;


import com.example.websocket_chat.global.exception.NotFoundException;

import static com.example.websocket_chat.global.error.ErrorStaticField.MEMBER_NOT_FOUND;

public class MemberNotFoundException extends NotFoundException {

    private static final String MESSAGE = MEMBER_NOT_FOUND;
    public MemberNotFoundException() {
        super(MESSAGE);
    }
}
