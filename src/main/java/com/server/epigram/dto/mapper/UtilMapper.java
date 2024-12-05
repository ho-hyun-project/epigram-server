package com.server.epigram.dto.mapper;

import com.server.epigram.auth.UserDetailsImpl;
import com.server.epigram.db.entity.Epigram;
import com.server.epigram.db.entity.User;
import com.server.epigram.db.repository.EpigramRepository;
import com.server.epigram.db.repository.UserRepository;
import com.server.epigram.dto.WriterDto;
import jakarta.persistence.EntityNotFoundException;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface UtilMapper {

    @Named("mapUser")
    default User mapUser(UserDetailsImpl userDetails, @Context UserRepository userRepository) {
        return userRepository.findById(userDetails.getId())
                .orElseThrow(() -> new EntityNotFoundException("해당 유저를 찾을 수 없습니다."));
    }

    @Named("mapEpigram")
    default Epigram mapEpigram(Long id, @Context EpigramRepository epigramRepository) {
        return epigramRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("해당 에피그램을 찾을 수 없습니다."));
    }

    @Named("mapWriter")
    default WriterDto mapWriter(User user) {
        return new WriterDto(user.getId(), user.getNickname(), user.getImage());
    }

}
