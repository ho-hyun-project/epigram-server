package com.server.epigram.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class CommentController {

    @PostMapping("/comments")
    public String createComments() {
        return "";
    }

    @GetMapping("/comments")
    public String getComments() {
        return "";
    }

    @PatchMapping("/comments/{id}")
    public String modifyComments(@PathVariable String id) {
        return "";
    }

    @DeleteMapping("/comments/{id}")
    public String deleteComments(@PathVariable String id) {
        return "";
    }

}
