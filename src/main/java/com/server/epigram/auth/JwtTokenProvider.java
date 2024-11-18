package com.server.epigram.auth;

import com.server.epigram.dto.JwtToken;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import java.util.Date;
import javax.crypto.SecretKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class JwtTokenProvider {

    private static final long ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * 60;
    private static final long REFRESH_TOKEN_EXPIRE_TIME = 1000 * 60 * 60 * 12 * 7;

    @Value("${spring.application.name}")
    private String issuer;

    SecretKey key = Jwts.SIG.HS256.key().build();

    public JwtToken generateToken(String email) {
        Date now = new Date();
        Date accessExpiredDate = new Date(now.getTime() + ACCESS_TOKEN_EXPIRE_TIME);
        Date refreshExpiredDate = new Date(now.getTime() + REFRESH_TOKEN_EXPIRE_TIME);

        String accessToken = Jwts.builder()
                .issuer(issuer)
                .subject(email)
                .issuedAt(now)
                .expiration(accessExpiredDate)
                .signWith(key)
                .compact();

        String refreshToken = Jwts.builder()
                .issuer(issuer)
                .subject(email)
                .issuedAt(now)
                .expiration(refreshExpiredDate)
                .signWith(key)
                .compact();

        return JwtToken.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    // TODO: 토큰 검증
    private Claims parseClaims(JwtToken jwtToken) {
        return null;
    }
}

