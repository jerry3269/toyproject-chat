package com.example.websocket_chat.login.member.service;


import com.example.websocket_chat.login.member.dto.SecretKey;
import com.example.websocket_chat.login.member.repository.RefreshTokenRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Date;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class JwtProvider {

    private final SecretKey secretKey;

    private final RefreshTokenRepository refreshTokenRepository;
    private final long accessTokenExpiration = 1000L * 60 * 15; // Access token is 15 minutes.
    private final long refreshTokenExpiration = 1000L * 60 * 60 * 24; // Refresh token is one day.

    public String createAccessToken(String userId) {
        byte[] decodedSecretKey = secretKey.getDecoded();
        return Jwts.builder()
                .setSubject(userId)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + accessTokenExpiration))
                .signWith(Keys.hmacShaKeyFor(decodedSecretKey))
                .compact();
    }

    public String createRefreshToken(String userId) {
        byte[] decodedSecretKey = secretKey.getDecoded();
        String refreshToken = Jwts.builder()
                .setSubject(userId)
                .setId(UUID.randomUUID().toString()) // Unique ID for refresh token
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + refreshTokenExpiration))
                .signWith(Keys.hmacShaKeyFor(decodedSecretKey))
                .compact();

        return refreshTokenRepository.save(userId, refreshToken);
    }

    public void setCookie(HttpServletResponse response, String refreshToken) {
        ResponseCookie cookie = ResponseCookie.from(HttpHeaders.SET_COOKIE, refreshToken)
                .httpOnly(true)
                .secure(true)
                .maxAge(Duration.ofDays(30))
                .build();
        response.setHeader(HttpHeaders.SET_COOKIE, cookie.toString());
    }

    public void expiredRefreshToken(String userId) {
        refreshTokenRepository.invalidate(userId);
    }
}
