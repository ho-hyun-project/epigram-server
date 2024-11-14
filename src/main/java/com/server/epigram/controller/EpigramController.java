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
        List<EpigramResponseDto> epigramResponseDtos = epigramService.getAllEpigram();
        return ResponseEntity.ok(epigramResponseDtos);
    }

    @GetMapping("/today")
    public ResponseEntity<EpigramResponseDto> getTodayEpigram() {
        EpigramResponseDto epigramResponseDto = epigramService.getTodayEpigram();
        return ResponseEntity.ok(epigramResponseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EpigramResponseDto> getEpigram(@PathVariable Long id) {
        EpigramResponseDto epigramResponseDto = epigramService.getEpigram(id);
        return ResponseEntity.ok(epigramResponseDto);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<EpigramResponseDto> modifyEpigram(@PathVariable Long id,
                                                            @RequestBody EpigramRequestDto epigramRequestDto) {
        EpigramResponseDto epigramResponseDto = epigramService.updateEpigram(id, epigramRequestDto);
        return ResponseEntity.ok(epigramResponseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEpigram(@PathVariable Long id) {
        epigramService.deleteEpigram(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/like")
    public String addEpigramLike(@PathVariable("id") Long id) {
        return "";
    }

    @DeleteMapping("/{id}/like")
    public String deleteEpigramLike(@PathVariable("id") String id) {
        return "";
    }

    @GetMapping("/{id}/comments")
    public String getEpigramAllComments(@PathVariable("id") String id) {
        return "";
    }

}
