package com.server.epigram.service;

import com.server.epigram.db.entity.Epigram;
import com.server.epigram.db.entity.Tag;
import com.server.epigram.db.repository.EpigramRepository;
import com.server.epigram.db.repository.TagRepository;
import com.server.epigram.dto.mapper.EpigramMapper;
import com.server.epigram.dto.mapper.TagMapper;
import com.server.epigram.dto.request.EpigramRequestDto;
import com.server.epigram.dto.response.EpigramResponseDto;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EpigramService {

    private final EpigramRepository epigramRepository;
    private final TagRepository tagRepository;
    private final EpigramMapper epigramMapper;
    private final TagMapper tagMapper;

    @Autowired
    public EpigramService(EpigramRepository epigramRepository,
                          TagRepository tagRepository,
                          EpigramMapper epigramMapper,
                          TagMapper tagMapper) {
        this.epigramRepository = epigramRepository;
        this.tagRepository = tagRepository;
        this.epigramMapper = epigramMapper;
        this.tagMapper = tagMapper;
    }

    @Transactional
    public EpigramResponseDto createEpigram(EpigramRequestDto epigramRequestDto) {
        Epigram epigram = epigramMapper.toEntity(epigramRequestDto);

        List<Tag> tags = epigramRequestDto.getTags().stream()
                .map(tagDto -> {
                    Tag tag = tagRepository.findByName(tagDto.getName())
                            .orElse(tagMapper.toEntity(tagDto));

                    return tagRepository.save(tag);
                }).toList();

        epigram.setTags(tags);

        Epigram savedEpigram = epigramRepository.save(epigram);

        return epigramMapper.toResponseDto(savedEpigram);
    }

    @Transactional
    public List<EpigramResponseDto> readAllEpigram() {
        List<Epigram> epigrams = epigramRepository.findAll();
        return epigramMapper.toDtoList(epigrams);
    }
}
