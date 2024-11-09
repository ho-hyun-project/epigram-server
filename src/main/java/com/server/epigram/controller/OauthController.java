package com.server.epigram.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OauthController {

    @PostMapping("/oauthApps")
    public String oauthLogin() {
        return "";
    }

}
