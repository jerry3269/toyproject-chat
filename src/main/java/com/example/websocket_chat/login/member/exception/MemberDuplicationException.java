package com.example.websocket_chat.login.member.exception;


import com.example.websocket_chat.global.exception.DuplicationException;

import static com.example.websocket_chat.global.error.ErrorStaticField.DUP_LOGIN_ID;

public class MemberDuplicationException extends DuplicationException {

    private static final String MESSAGE = DUP_LOGIN_ID;

    public MemberDuplicationException() {
        super(MESSAGE);
    }
}
