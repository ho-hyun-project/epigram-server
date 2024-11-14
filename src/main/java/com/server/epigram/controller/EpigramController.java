package com.server.epigram.controller;

import com.server.epigram.dto.request.EpigramRequestDto;
import com.server.epigram.dto.response.EpigramResponseDto;
import com.server.epigram.service.EpigramService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/epigrams")
public class EpigramController {

    private final EpigramService epigramService;

    @Autowired
    public EpigramController(EpigramService epigramService) {
        this.epigramService = epigramService;
    }

    @PostMapping
    public ResponseEntity<EpigramResponseDto> postEpigram(@RequestBody EpigramRequestDto epigramRequestDto) {

        EpigramResponseDto epigramResponseDto = epigramService.createEpigram(epigramRequestDto);
        return ResponseEntity.ok(epigramResponseDto);

    }

    @GetMapping
    public ResponseEntity<List<EpigramResponseDto>> getAllEpigram() {

        List<EpigramResponseDto> epigramResponseDtos = epigramService.readAllEpigram();
        return ResponseEntity.ok(epigramResponseDtos);
    }

    @GetMapping("/today")
    public String getTodayEpigram() {
        return "";
    }

    @GetMapping("/{id}")
    public String getEpigram(@PathVariable String id) {
        return "";
    }

    @PatchMapping("/{id}")
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
