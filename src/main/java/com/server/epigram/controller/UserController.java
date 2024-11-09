package com.server.epigram.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/users/me")
    public String getUserInfo() {
        return "";
    }

    @PatchMapping("/users/me")
    public String modifyUserInfo() {
        return "";
    }

    @GetMapping("/users/me/epigrams")
    public String getUserEpigram() {
        return "";
    }

    @GetMapping("/users/me/comments")
    public String getUserComment() {
        return "";
    }

}
