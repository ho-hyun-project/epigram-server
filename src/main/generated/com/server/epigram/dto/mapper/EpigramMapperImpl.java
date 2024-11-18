package com.server.epigram.dto.mapper;

import com.server.epigram.db.entity.Epigram;
import com.server.epigram.db.entity.Tag;
import com.server.epigram.dto.TagDto;
import com.server.epigram.dto.request.EpigramRequestDto;
import com.server.epigram.dto.response.epigram.EpigramResponseDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-18T16:44:11+0900",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.13 (Amazon.com Inc.)"
)
@Component
public class EpigramMapperImpl implements EpigramMapper {

    @Autowired
    private TagMapper tagMapper;

    @Override
    public Epigram toEntity(EpigramRequestDto epigramRequestDto) {
        if ( epigramRequestDto == null ) {
            return null;
        }

        Epigram epigram = new Epigram();

        epigram.setReferenceUrl( epigramRequestDto.getReferenceUrl() );
        epigram.setReferenceTitle( epigramRequestDto.getReferenceTitle() );
        epigram.setAuthor( epigramRequestDto.getAuthor() );
        epigram.setContent( epigramRequestDto.getContent() );
        epigram.setTags( tagDtoListToTagList( epigramRequestDto.getTags() ) );

        return epigram;
    }

    @Override
    public EpigramResponseDto toResponseDto(Epigram epigram) {
        if ( epigram == null ) {
            return null;
        }

        EpigramResponseDto.EpigramResponseDtoBuilder epigramResponseDto = EpigramResponseDto.builder();

        epigramResponseDto.id( epigram.getId() );
        epigramResponseDto.referenceUrl( epigram.getReferenceUrl() );
        epigramResponseDto.referenceTitle( epigram.getReferenceTitle() );
        epigramResponseDto.author( epigram.getAuthor() );
        epigramResponseDto.content( epigram.getContent() );
        epigramResponseDto.tags( tagListToTagDtoList( epigram.getTags() ) );

        return epigramResponseDto.build();
    }

    @Override
    public List<EpigramResponseDto> toDtoList(List<Epigram> epigrams) {
        if ( epigrams == null ) {
            return null;
        }

        List<EpigramResponseDto> list = new ArrayList<EpigramResponseDto>( epigrams.size() );
        for ( Epigram epigram : epigrams ) {
            list.add( toResponseDto( epigram ) );
        }

        return list;
    }

    protected List<Tag> tagDtoListToTagList(List<TagDto> list) {
        if ( list == null ) {
            return null;
        }

        List<Tag> list1 = new ArrayList<Tag>( list.size() );
        for ( TagDto tagDto : list ) {
            list1.add( tagMapper.toEntity( tagDto ) );
        }

        return list1;
    }

    protected List<TagDto> tagListToTagDtoList(List<Tag> list) {
        if ( list == null ) {
            return null;
        }

        List<TagDto> list1 = new ArrayList<TagDto>( list.size() );
        for ( Tag tag : list ) {
            list1.add( tagMapper.fromEntity( tag ) );
        }

        return list1;
    }
}
