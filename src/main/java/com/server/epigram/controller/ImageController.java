package com.server.epigram.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ImageController {

    @PostMapping("/images/upload")
    public String addImage() {
        return "";
    }

}
