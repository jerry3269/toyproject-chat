package com.example.websocket_chat.login.member.exception;


import com.example.websocket_chat.global.exception.UnauthorizedException;

import static com.example.websocket_chat.global.error.ErrorStaticField.MEMBER_UNAUTHORIZED;

public class MemberUnauthorizedException extends UnauthorizedException {

    private static final String MESSAGE = MEMBER_UNAUTHORIZED;
    public MemberUnauthorizedException() {
        super(MESSAGE);
    }
}
