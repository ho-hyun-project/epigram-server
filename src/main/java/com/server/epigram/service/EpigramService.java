package com.server.epigram.service;

import com.server.epigram.db.entity.Epigram;
import com.server.epigram.db.entity.Tag;
import com.server.epigram.db.repository.EpigramRepository;
import com.server.epigram.db.repository.TagRepository;
import com.server.epigram.dto.TagDto;
import com.server.epigram.dto.request.EpigramRequestDto;
import com.server.epigram.dto.response.EpigramResponseDto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EpigramService {

    private final EpigramRepository epigramRepository;
    private final TagRepository tagRepository;

    @Autowired
    public EpigramService(EpigramRepository epigramRepository, TagRepository tagRepository) {
        this.epigramRepository = epigramRepository;
        this.tagRepository = tagRepository;
    }

    public EpigramResponseDto createEpigram(EpigramRequestDto epigramRequestDto) {
        //TODO : 로그인한 유저 id 조회 후 응답값에 포함
        Epigram epigram = new Epigram();
        epigram.setAuthor(epigramRequestDto.getAuthor());
        epigram.setContent(epigramRequestDto.getContent());
        epigram.setReferenceTitle(epigramRequestDto.getReferenceTitle());
        epigram.setReferenceUrl(epigramRequestDto.getReferenceUrl());

        List<Tag> tags = epigramRequestDto.getTags().stream()
                .map(TagDto::toEntity)
                .map(tagRepository::save)
                .toList();

        epigram.setTags(tags);

        epigramRepository.save(epigram);

        List<TagDto> tagDtos = tags.stream()
                .map(TagDto::fromEntity)
                .toList();

        return EpigramResponseDto.builder()
                .id(epigram.getId())
                .referenceTitle(epigram.getReferenceTitle())
                .referenceUrl(epigram.getReferenceUrl())
                .author(epigram.getAuthor())
                .content(epigram.getContent())
                .writerId(1L)
                .tags(tagDtos)
                .likeCount(0L)
                .build();
    }
}
