package com.server.epigram.dto.mapper;

import com.server.epigram.db.entity.User;
import com.server.epigram.dto.request.auth.RegisterRequestDto;
import com.server.epigram.dto.response.auth.AuthResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(RegisterRequestDto requestDto);

    AuthResponseDto toResponseDto(User user);
}
