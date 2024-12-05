package com.server.epigram.controller;

import com.server.epigram.auth.UserDetailsImpl;
import com.server.epigram.dto.request.CommentRequestDto;
import com.server.epigram.dto.response.CommentResponseDto;
import com.server.epigram.service.CommentService;
import io.swagger.v3.oas.annotations.tags.Tag;
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

@Tag(name = "Comment")
@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("")
    public ResponseEntity<CommentResponseDto> postComment(@RequestBody CommentRequestDto commentRequestDto,
                                                          @AuthenticationPrincipal
                                                          UserDetailsImpl userDetails) {
        CommentResponseDto commentResponseDto = commentService.createComment(userDetails, commentRequestDto);
        return ResponseEntity.ok(commentResponseDto);
    }

    @GetMapping("")
    public String getComments() {
        return "";
    }

    @PatchMapping("/{id}")
    public String modifyComments(@PathVariable String id) {
        return "";
    }

    @DeleteMapping("/{id}")
    public String deleteComments(@PathVariable String id) {
        return "";
    }

}
