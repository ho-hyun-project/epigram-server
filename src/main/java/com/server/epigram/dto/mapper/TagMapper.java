package com.server.epigram.dto.mapper;

import com.server.epigram.db.entity.Tag;
import com.server.epigram.dto.TagDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TagMapper {

    Tag toEntity(TagDto tagDto);

    TagDto fromEntity(Tag tag);

}
