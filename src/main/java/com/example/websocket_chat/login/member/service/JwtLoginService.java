package com.example.websocket_chat.login.member.service;



import com.example.websocket_chat.login.member.domain.Member;
import com.example.websocket_chat.login.member.domain.MemberSession;
import com.example.websocket_chat.login.member.dto.LoginForm;
import com.example.websocket_chat.login.member.dto.MemberWithTokenDto;
import com.example.websocket_chat.login.member.dto.SignUpForm;
import com.example.websocket_chat.login.member.exception.MemberNotFoundException;
import com.example.websocket_chat.login.member.exception.MemberPasswordNotMatchException;
import com.example.websocket_chat.login.member.repository.MemberRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class JwtLoginService {
    private final JwtMemberService jwtMemberService;
    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;
    private final JwtProvider jwtProvider;

    @Transactional
    public MemberWithTokenDto signUp(SignUpForm signUpForm) {
        return jwtMemberService.save(signUpForm);
    }

    public MemberWithTokenDto login(LoginForm loginForm, HttpServletRequest request, HttpServletResponse response) {
        Member member = memberRepository.findByUserId(loginForm.userId()).orElseThrow(MemberNotFoundException::new);
        if (passwordEncoder.matches(loginForm.password(), member.getPassword())) {
            String accessToken = jwtProvider.createAccessToken(loginForm.userId());
            String refreshToken = jwtProvider.createRefreshToken(loginForm.userId());
            jwtProvider.setCookie(response, refreshToken);
            return MemberWithTokenDto.from(member, accessToken);
        }
        throw new MemberPasswordNotMatchException();
    }

    public void logout(MemberSession memberSession) {
        jwtProvider.expiredRefreshToken(memberSession.userId());
    }
}
