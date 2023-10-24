package com.example.websocket_chat.login.member.exception;


import com.example.websocket_chat.global.exception.UnauthorizedException;

import static com.example.websocket_chat.global.error.ErrorStaticField.USER_UNAUTHORIZED;

public class MemberUnauthorizedException extends UnauthorizedException {

    private static final String MESSAGE = USER_UNAUTHORIZED;
    public MemberUnauthorizedException() {
        super(MESSAGE);
    }
}
