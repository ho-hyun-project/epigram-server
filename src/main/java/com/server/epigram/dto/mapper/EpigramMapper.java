package com.server.epigram.dto.mapper;

import com.server.epigram.db.entity.Epigram;
import com.server.epigram.dto.request.EpigramRequestDto;
import com.server.epigram.dto.response.epigram.EpigramResponseDto;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = TagMapper.class)
public interface EpigramMapper {

    Epigram toEntity(EpigramRequestDto epigramRequestDto);

    EpigramResponseDto toResponseDto(Epigram epigram);

    List<EpigramResponseDto> toDtoList(List<Epigram> epigrams);

}
