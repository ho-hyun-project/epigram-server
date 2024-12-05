package com.server.epigram.dto.mapper;

import com.server.epigram.auth.UserDetailsImpl;
import com.server.epigram.db.entity.Epigram;
import com.server.epigram.db.entity.Tag;
import com.server.epigram.db.entity.User;
import com.server.epigram.db.repository.UserRepository;
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
    date = "2024-11-19T21:59:42+0900",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.13 (Amazon.com Inc.)"
)
@Component
public class EpigramMapperImpl implements EpigramMapper {

    @Autowired
    private UtilMapper utilMapper;

    @Override
    public Epigram toEntity(UserDetailsImpl userDetails, EpigramRequestDto epigramRequestDto, UserRepository userRepository) {
        if ( userDetails == null && epigramRequestDto == null ) {
            return null;
        }

        Epigram epigram = new Epigram();

        if ( userDetails != null ) {
            epigram.setUser( utilMapper.mapUser( userDetails, userRepository ) );
            epigram.setId( userDetails.getId() );
        }
        if ( epigramRequestDto != null ) {
            epigram.setReferenceUrl( epigramRequestDto.getReferenceUrl() );
            epigram.setReferenceTitle( epigramRequestDto.getReferenceTitle() );
            epigram.setAuthor( epigramRequestDto.getAuthor() );
            epigram.setContent( epigramRequestDto.getContent() );
            epigram.setTags( tagDtoListToTagList( epigramRequestDto.getTags(), userRepository ) );
        }

        return epigram;
    }

    @Override
    public EpigramResponseDto toResponseDto(Epigram epigram) {
        if ( epigram == null ) {
            return null;
        }

        EpigramResponseDto.EpigramResponseDtoBuilder epigramResponseDto = EpigramResponseDto.builder();

        epigramResponseDto.writerId( epigramUserId( epigram ) );
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

    protected Tag tagDtoToTag(TagDto tagDto, UserRepository userRepository) {
        if ( tagDto == null ) {
            return null;
        }

        Tag tag = new Tag();

        tag.setName( tagDto.getName() );

        return tag;
    }

    protected List<Tag> tagDtoListToTagList(List<TagDto> list, UserRepository userRepository) {
        if ( list == null ) {
            return null;
        }

        List<Tag> list1 = new ArrayList<Tag>( list.size() );
        for ( TagDto tagDto : list ) {
            list1.add( tagDtoToTag( tagDto, userRepository ) );
        }

        return list1;
    }

    private Long epigramUserId(Epigram epigram) {
        if ( epigram == null ) {
            return null;
        }
        User user = epigram.getUser();
        if ( user == null ) {
            return null;
        }
        Long id = user.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected TagDto tagToTagDto(Tag tag) {
        if ( tag == null ) {
            return null;
        }

        TagDto tagDto = new TagDto();

        tagDto.setName( tag.getName() );

        return tagDto;
    }

    protected List<TagDto> tagListToTagDtoList(List<Tag> list) {
        if ( list == null ) {
            return null;
        }

        List<TagDto> list1 = new ArrayList<TagDto>( list.size() );
        for ( Tag tag : list ) {
            list1.add( tagToTagDto( tag ) );
        }

        return list1;
    }
}
