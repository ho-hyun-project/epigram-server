package com.server.epigram.dto.mapper;

import com.server.epigram.db.entity.User;
import com.server.epigram.dto.UserDto;
import com.server.epigram.dto.request.auth.RegisterRequestDto;
import com.server.epigram.dto.response.auth.AuthResponseDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-19T21:59:42+0900",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.13 (Amazon.com Inc.)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User toEntity(RegisterRequestDto requestDto) {
        if ( requestDto == null ) {
            return null;
        }

        User user = new User();

        user.setEmail( requestDto.getEmail() );
        user.setPassword( requestDto.getPassword() );
        user.setNickname( requestDto.getNickname() );

        return user;
    }

    @Override
    public AuthResponseDto toResponseDto(User user) {
        if ( user == null ) {
            return null;
        }

        AuthResponseDto.AuthResponseDtoBuilder authResponseDto = AuthResponseDto.builder();

        authResponseDto.user( userToUserDto( user ) );

        return authResponseDto.build();
    }

    protected UserDto userToUserDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto.UserDtoBuilder userDto = UserDto.builder();

        userDto.id( user.getId() );
        userDto.email( user.getEmail() );
        userDto.nickname( user.getNickname() );
        userDto.image( user.getImage() );
        userDto.createdAt( user.getCreatedAt() );
        userDto.updatedAt( user.getUpdatedAt() );

        return userDto.build();
    }
}
