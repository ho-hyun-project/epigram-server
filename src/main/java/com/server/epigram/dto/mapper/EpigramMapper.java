package com.server.epigram.dto.mapper;

import com.server.epigram.db.entity.Epigram;
import com.server.epigram.dto.request.EpigramRequestDto;
import com.server.epigram.dto.response.epigram.EpigramResponseDto;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UserMapper.class, TagMapper.class})
public interface EpigramMapper {

    @Mapping(target = "user", source = "userId", qualifiedByName = "mapUser")
    Epigram toEntity(EpigramRequestDto epigramRequestDto);

    EpigramResponseDto toResponseDto(Epigram epigram);

    List<EpigramResponseDto> toDtoList(List<Epigram> epigrams);

}
