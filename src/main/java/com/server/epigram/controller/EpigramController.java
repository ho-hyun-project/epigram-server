package com.server.epigram.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class EpigramController {

    @PostMapping("/epigrams")
    public String createEpigram() {
        return "";
    }

    @GetMapping("/epigrams")
    public String getAllEpigram() {
        return "";
    }

    @GetMapping("/epigrams/today")
    public String getTodayEpigram() {
        return "";
    }

    @GetMapping("/epigrams/{id}")
    public String getEpigram(@PathVariable String id) {
        return "";
    }

    @PatchMapping("/epigrams/{id}")
    public String modifyEpigram(@PathVariable String id) {
        return "";
    }

    @DeleteMapping("/epigrams/{id}")
    public String deleteEpigram(@PathVariable String id) {
        return "";
    }

    @PostMapping("/epigrams/{id}/like")
    public String addEpigramLike(@PathVariable String id) {
        return "";
    }

    @DeleteMapping("/epigrams/{id}/like")
    public String deleteEpigramLike(@PathVariable String id) {
        return "";
    }

    @GetMapping("/epigrams/{id}/comments")
    public String getEpigramAllComments(@PathVariable String id) {
        return "";
    }

}
