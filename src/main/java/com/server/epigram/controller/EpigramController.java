package com.server.epigram.controller;

import com.server.epigram.auth.UserDetailsImpl;
import com.server.epigram.dto.request.EpigramRequestDto;
import com.server.epigram.dto.response.epigram.EpigramResponseDto;
import com.server.epigram.dto.response.epigram.LikeEpigramResponseDto;
import com.server.epigram.exception.ErrorResponse;
import com.server.epigram.exception.InvalidDataException;
import com.server.epigram.service.EpigramService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Epigram")
@RestController
@RequestMapping("/api/epigrams")
public class EpigramController {

    private final EpigramService epigramService;

    @Autowired
    public EpigramController(EpigramService epigramService) {
        this.epigramService = epigramService;
    }

    @Operation(
            summary = "에피그램 등록",
            description = "새로운 에피그램을 생성합니다."
    )
    @ApiResponse(responseCode = "200", description = "성공",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = EpigramResponseDto.class)
            ))
    @ApiResponse(responseCode = "400", description = "잘못된 데이터 형식",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)
            ))
    @PostMapping
    public ResponseEntity<EpigramResponseDto> postEpigram(
            @Parameter(description = "생성할 에피그램 데이터") @RequestBody EpigramRequestDto epigramRequestDto,
            @AuthenticationPrincipal
            UserDetailsImpl userDetails) {

        if (epigramRequestDto == null || epigramRequestDto.getContent().isEmpty()) {
            throw new InvalidDataException("잘못된 형식입니다.");
        }

        EpigramResponseDto epigramResponseDto = epigramService.createEpigram(userDetails, epigramRequestDto);
        return ResponseEntity.ok(epigramResponseDto);
    }

    @GetMapping
    public ResponseEntity<List<EpigramResponseDto>> getAllEpigram(
            @AuthenticationPrincipal
            UserDetailsImpl userDetails) {
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
    public ResponseEntity<LikeEpigramResponseDto> addEpigramLike(@PathVariable("id") Long epigramId,
                                                                 @AuthenticationPrincipal
                                                                 UserDetailsImpl userDetails) {
        Long userId = userDetails.getId();
        LikeEpigramResponseDto likeEpigramResponseDto = epigramService.addLikeEpigram(userId, epigramId);
        return ResponseEntity.ok(likeEpigramResponseDto);
    }

    @DeleteMapping("/{id}/like")
    public ResponseEntity<LikeEpigramResponseDto> deleteEpigramLike(@PathVariable("id") Long epigramId,
                                                                    @AuthenticationPrincipal
                                                                    UserDetailsImpl userDetails) {
        Long userId = userDetails.getId();
        LikeEpigramResponseDto likeEpigramResponseDto = epigramService.deleteLikeEpigram(userId, epigramId);
        return ResponseEntity.ok(likeEpigramResponseDto);
    }

    @GetMapping("/{id}/comments")
    public String getEpigramAllComments(@PathVariable String id) {
        return "";
    }

}
