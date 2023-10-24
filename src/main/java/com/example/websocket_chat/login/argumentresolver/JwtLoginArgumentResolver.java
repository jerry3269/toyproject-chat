package com.example.websocket_chat.login.argumentresolver;

import com.example.websocket_chat.login.member.domain.Member;
import com.example.websocket_chat.login.member.domain.MemberSession;
import com.example.websocket_chat.login.member.exception.MemberNotFoundException;
import com.example.websocket_chat.login.member.repository.MemberRepository;
import com.example.websocket_chat.login.member.service.JwtValidateService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@RequiredArgsConstructor
@Slf4j
public class JwtLoginArgumentResolver implements HandlerMethodArgumentResolver {

    private final MemberRepository memberRepository;
    private final JwtValidateService jwtValidateService;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        log.info("JwtLoginArgumentResolver supportsParameter 실행");
        boolean hasLoginAnnotation = parameter.hasParameterAnnotation(JwtLogin.class);
        boolean hasMemberSessionType = MemberSession.class.isAssignableFrom(parameter.getParameterType());
        return hasLoginAnnotation && hasMemberSessionType;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        log.info("JwtLoginArgumentResolver resolveArgument 실행");
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();

        Claims claims = jwtValidateService.validateToken(request);
        String userId = claims.getSubject();
        Member member = memberRepository.findByUserId(userId).orElseThrow(MemberNotFoundException::new);
        return MemberSession.from(member);
    }
}
