package com.server.epigram.dto.mapper;

import com.server.epigram.auth.UserDetailsImpl;
import com.server.epigram.db.entity.Epigram;
import com.server.epigram.db.repository.UserRepository;
import com.server.epigram.dto.request.EpigramRequestDto;
import com.server.epigram.dto.response.epigram.EpigramResponseDto;
import java.util.List;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UtilMapper.class})
public interface EpigramMapper {

    @Mapping(target = "user", source = "userDetails", qualifiedByName = "mapUser")
    Epigram toEntity(UserDetailsImpl userDetails, EpigramRequestDto epigramRequestDto,
                     @Context UserRepository userRepository);

    @Mapping(target = "writerId", source = "user.id")
    EpigramResponseDto toResponseDto(Epigram epigram);

    List<EpigramResponseDto> toDtoList(List<Epigram> epigrams);

}
