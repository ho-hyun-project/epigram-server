package com.server.epigram.service;

import com.server.epigram.db.entity.Epigram;
import com.server.epigram.db.entity.Tag;
import com.server.epigram.db.repository.EpigramRepository;
import com.server.epigram.db.repository.TagRepository;
import com.server.epigram.dto.mapper.EpigramMapper;
import com.server.epigram.dto.mapper.TagMapper;
import com.server.epigram.dto.request.EpigramRequestDto;
import com.server.epigram.dto.response.EpigramResponseDto;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional(readOnly = true)
    public List<EpigramResponseDto> getAllEpigram() {
        List<Epigram> epigrams = epigramRepository.findAll();

        if (epigrams.isEmpty()) {
            throw new EntityNotFoundException("등록된 글귀가 없습니다. 새로운 글귀를 추가해주세요.");
        }

        return epigramMapper.toDtoList(epigrams);
    }

    @Transactional(readOnly = true)
    public EpigramResponseDto getTodayEpigram() {
        Epigram randomEpigram = epigramRepository.findRandomEpigram()
                .orElseThrow(() -> new EntityNotFoundException("저장된 에피그램이 없습니다. 에피그램을 추가해주세요."));

        return epigramMapper.toResponseDto(randomEpigram);
    }

    @Transactional(readOnly = true)
    public EpigramResponseDto getEpigram(Long id) {
        Epigram epigram = epigramRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("해당하는 데이터는 존재하지 않습니다."));

        return epigramMapper.toResponseDto(epigram);
    }

    @Transactional
    public EpigramResponseDto updateEpigram(Long id, EpigramRequestDto epigramRequestDto) {
        Epigram epigram = epigramRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("요청한 ID와 일치하는 데이터가 없습니다."));

        epigram.setReferenceUrl(epigramRequestDto.getReferenceUrl());
        epigram.setReferenceTitle(epigramRequestDto.getReferenceTitle());
        epigram.setAuthor(epigramRequestDto.getAuthor());
        epigram.setContent(epigramRequestDto.getContent());

        Epigram savedEpigram = epigramRepository.save(epigram);

        return epigramMapper.toResponseDto(savedEpigram);
    }

    @Transactional
    public void deleteEpigram(Long id) {
        epigramRepository.deleteById(id);
    }

}