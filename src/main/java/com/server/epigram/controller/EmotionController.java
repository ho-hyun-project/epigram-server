package com.server.epigram.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class EmotionController {

    @PostMapping("/emotionLogs/today")
    public String addTodayEmotion() {
        return "";
    }

    @GetMapping("/emotionLogs/today")
    public String getTodayEmotion() {
        return "";
    }

    @GetMapping("/emotionLogs/monthly")
    public String getMonthlyEmotion() {
        return "";
    }

}
