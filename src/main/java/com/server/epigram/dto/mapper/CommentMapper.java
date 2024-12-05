package com.server.epigram.dto.mapper;

import com.server.epigram.auth.UserDetailsImpl;
import com.server.epigram.db.entity.Comment;
import com.server.epigram.db.repository.EpigramRepository;
import com.server.epigram.db.repository.UserRepository;
import com.server.epigram.dto.request.CommentRequestDto;
import com.server.epigram.dto.response.CommentResponseDto;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UtilMapper.class})
public interface CommentMapper {

    @Mapping(target = "user", source = "userDetails", qualifiedByName = "mapUser")
    @Mapping(target = "epigram", source = "requestDto.epigramId", qualifiedByName = "mapEpigram")
    Comment toEntity(UserDetailsImpl userDetails, CommentRequestDto requestDto,
                     @Context EpigramRepository epigramRepository, @Context
                     UserRepository userRepository);

    @Mapping(target = "writer", source = "user", qualifiedByName = "mapWriter")
    CommentResponseDto toResponseDto(Comment comment);
}
