package com.server.epigram.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @PostMapping("/auth/signUp")
    public String signUp() {
        return "";
    }

    @PostMapping("/auth/signIn")
    public String signIn() {
        return "";
    }

    @PostMapping("/auth/refresh-token")
    public String getRefreshToken() {
        return "";
    }

    @PostMapping("/auth/signIn/{provider}")
    public String signInOauth(@PathVariable String provider) {
        return "";
    }

}
