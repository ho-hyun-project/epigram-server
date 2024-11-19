package com.server.epigram.dto.mapper;

import com.server.epigram.db.entity.Comment;
import com.server.epigram.dto.request.CommentRequestDto;
import com.server.epigram.dto.response.CommentResponseDto;
import com.server.epigram.service.EpigramService;
import com.server.epigram.service.UserService;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {EpigramMapper.class, UserMapper.class})
public interface CommentMapper {

    @Mapping(target = "epigram", source = "epigramId", qualifiedByName = "mapEpigram")
    @Mapping(target = "user", source = "userId", qualifiedByName = "mapUser")
    Comment toEntity(CommentRequestDto requestDto, @Context EpigramService epigramService,
                     @Context UserService userService);

    CommentResponseDto toResponseDto(Comment comment);

}
