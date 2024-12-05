package com.server.epigram.service;

import com.server.epigram.auth.UserDetailsImpl;
import com.server.epigram.db.entity.Comment;
import com.server.epigram.db.repository.CommentRepository;
import com.server.epigram.db.repository.EpigramRepository;
import com.server.epigram.db.repository.UserRepository;
import com.server.epigram.dto.mapper.CommentMapper;
import com.server.epigram.dto.request.CommentRequestDto;
import com.server.epigram.dto.response.CommentResponseDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final EpigramRepository epigramRepository;
    private final UserRepository userRepository;
    private final CommentMapper commentMapper;

    public CommentResponseDto createComment(UserDetailsImpl userDetails, CommentRequestDto commentRequestDto) {
        Comment comment = commentMapper.toEntity(userDetails, commentRequestDto, epigramRepository, userRepository);
        commentRepository.save(comment);

        return commentMapper.toResponseDto(comment);
    }
}
