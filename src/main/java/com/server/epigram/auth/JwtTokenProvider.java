package com.server.epigram.auth;

import com.server.epigram.dto.JwtToken;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import java.util.Base64;
import java.util.Date;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    private static final long ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * 60;
    private static final long REFRESH_TOKEN_EXPIRE_TIME = 1000 * 60 * 60 * 12 * 7;

    private final UserDetailsServiceImpl userDetailsService;

    @Value("${spring.application.name}")
    private String issuer;

    @Value("${spring.jwt.secret}")
    private String secretKey;


    public JwtToken generateToken(String email) {
        Date now = new Date();
        Date accessExpiredDate = new Date(now.getTime() + ACCESS_TOKEN_EXPIRE_TIME);
        Date refreshExpiredDate = new Date(now.getTime() + REFRESH_TOKEN_EXPIRE_TIME);

        SecretKey key = getSecretKey();

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

    public Authentication getAuthentication(String jwt) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(getUserEmail(jwt));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String getUserEmail(String token) {
        return parseClaims(token).getSubject();
    }

    public boolean validateToken(String token) {
        if (!StringUtils.hasText(token)) {
            return false;
        }

        Claims claims = parseClaims(token);
        return !claims.getExpiration().before(new Date());
    }

    private Claims parseClaims(String token) {
        try {
            return Jwts.parser().verifyWith(getSecretKey()).build().parseSignedClaims(token).getPayload();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }

    private SecretKey getSecretKey() {
        return new SecretKeySpec(Base64.getDecoder().decode(secretKey), "HmacSHA256");
    }

}

