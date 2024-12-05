package com.server.epigram.dto.mapper;

import com.server.epigram.auth.UserDetailsImpl;
import com.server.epigram.db.entity.Comment;
import com.server.epigram.db.repository.EpigramRepository;
import com.server.epigram.db.repository.UserRepository;
import com.server.epigram.dto.request.CommentRequestDto;
import com.server.epigram.dto.response.CommentResponseDto;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-19T21:59:42+0900",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.13 (Amazon.com Inc.)"
)
@Component
public class CommentMapperImpl implements CommentMapper {

    @Autowired
    private UtilMapper utilMapper;

    @Override
    public Comment toEntity(UserDetailsImpl userDetails, CommentRequestDto requestDto, EpigramRepository epigramRepository, UserRepository userRepository) {
        if ( userDetails == null && requestDto == null ) {
            return null;
        }

        Comment comment = new Comment();

        if ( userDetails != null ) {
            comment.setUser( utilMapper.mapUser( userDetails, userRepository ) );
            comment.setId( userDetails.getId() );
        }
        if ( requestDto != null ) {
            comment.setEpigram( utilMapper.mapEpigram( requestDto.getEpigramId(), epigramRepository ) );
            comment.setContent( requestDto.getContent() );
            comment.setPrivate( requestDto.isPrivate() );
        }

        return comment;
    }

    @Override
    public CommentResponseDto toResponseDto(Comment comment) {
        if ( comment == null ) {
            return null;
        }

        CommentResponseDto.CommentResponseDtoBuilder commentResponseDto = CommentResponseDto.builder();

        commentResponseDto.writer( utilMapper.mapWriter( comment.getUser() ) );
        commentResponseDto.id( comment.getId() );
        commentResponseDto.content( comment.getContent() );
        commentResponseDto.createdAt( comment.getCreatedAt() );
        commentResponseDto.updatedAt( comment.getUpdatedAt() );

        return commentResponseDto.build();
    }
}
