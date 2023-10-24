package com.example.websocket_chat.login.member.service;

import com.example.websocket_chat.login.member.domain.Member;
import com.example.websocket_chat.login.member.domain.MemberSession;
import com.example.websocket_chat.login.member.dto.MemberWithTokenDto;
import com.example.websocket_chat.login.member.dto.SignUpForm;
import com.example.websocket_chat.login.member.exception.MemberDuplicationException;
import com.example.websocket_chat.login.member.exception.MemberNotFoundException;
import com.example.websocket_chat.login.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class JwtMemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public MemberWithTokenDto save(SignUpForm signUpForm) {
        validDupUserId(signUpForm.userId());
        return MemberWithTokenDto.withoutToken(memberRepository.save(signUpForm.toEntity(passwordEncoder)));
    }

    private void validDupUserId(String userId) {
        Optional<Member> optionalUser = memberRepository.findByUserId(userId);
        if (optionalUser.isPresent()) {
            throw new MemberDuplicationException();
        }
    }

    public MemberWithTokenDto getBySession(MemberSession session) {
        return memberRepository.findByUserId(session.userId())
                .map(MemberWithTokenDto::withoutToken)
                .orElseThrow(MemberNotFoundException::new);
    }

    @Transactional
    public void delete(MemberSession memberSession) {
        memberRepository.deleteById(memberSession.id());
    }
}
