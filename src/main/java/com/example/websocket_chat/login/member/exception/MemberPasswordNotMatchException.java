package com.example.websocket_chat.login.member.exception;


import com.example.websocket_chat.global.exception.NotMatchException;

import static com.example.websocket_chat.global.error.ErrorStaticField.INVALID_PASSWORD;

public class MemberPasswordNotMatchException extends NotMatchException {

    private static final String MESSAGE = INVALID_PASSWORD;
    public MemberPasswordNotMatchException() {
        super(MESSAGE);
    }
}
