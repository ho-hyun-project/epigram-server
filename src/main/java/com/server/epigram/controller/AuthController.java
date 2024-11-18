package com.server.epigram.controller;

import com.server.epigram.dto.request.auth.LoginRequestDto;
import com.server.epigram.dto.request.auth.RegisterRequestDto;
import com.server.epigram.dto.response.auth.AuthResponseDto;
import com.server.epigram.service.AuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Auth")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signUp")
    public ResponseEntity<AuthResponseDto> signUp(@RequestBody RegisterRequestDto registerRequestDto) {
        AuthResponseDto authResponseDto = authService.register(registerRequestDto);
        return ResponseEntity.ok(authResponseDto);
    }

    @PostMapping("/signIn")
    public ResponseEntity<AuthResponseDto> signIn(@RequestBody LoginRequestDto loginRequestDto) {
        AuthResponseDto authResponseDto = authService.authenticate(loginRequestDto);
        return ResponseEntity.ok(authResponseDto);
    }

    @PostMapping("/refresh-token")
    public String getRefreshToken() {
        return "";
    }

    @PostMapping("/signIn/{provider}")
    public String signInOauth(@PathVariable String provider) {
        return "";
    }

}
