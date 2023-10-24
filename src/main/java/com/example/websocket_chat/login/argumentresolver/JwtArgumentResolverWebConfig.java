package com.example.websocket_chat.login.argumentresolver;


import com.example.websocket_chat.login.member.repository.MemberRepository;
import com.example.websocket_chat.login.member.service.JwtValidateService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@RequiredArgsConstructor
@Configuration
public class JwtArgumentResolverWebConfig implements WebMvcConfigurer {

    private final MemberRepository memberRepository;
    private final JwtValidateService jwtValidateService;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new JwtLoginArgumentResolver(memberRepository, jwtValidateService));
    }
}
