package com.server.epigram.service;

import com.server.epigram.db.entity.Epigram;
import com.server.epigram.db.entity.EpigramLike;
import com.server.epigram.db.entity.Tag;
import com.server.epigram.db.entity.User;
import com.server.epigram.db.repository.EpigramLikeRepository;
import com.server.epigram.db.repository.EpigramRepository;
import com.server.epigram.db.repository.TagRepository;
import com.server.epigram.db.repository.UserRepository;
import com.server.epigram.dto.mapper.EpigramMapper;
import com.server.epigram.dto.mapper.TagMapper;
import com.server.epigram.dto.request.EpigramRequestDto;
import com.server.epigram.dto.response.epigram.EpigramResponseDto;
import com.server.epigram.dto.response.epigram.LikeEpigramResponseDto;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@AllArgsConstructor
public class EpigramService {

    private final EpigramRepository epigramRepository;
    private final TagRepository tagRepository;
    private final EpigramLikeRepository epigramLikeRepository;
    private final UserRepository userRepository;
    private final EpigramMapper epigramMapper;
    private final TagMapper tagMapper;

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

    @Transactional
    public LikeEpigramResponseDto addLikeEpigram(Long userId, Long epigramId) {
        EpigramResponseDto epigram = getEpigram(epigramId);
        boolean isLiked = epigramLikeRepository.existsByUserIdAndEpigramId(userId, epigramId);

        if (isLiked) {
            LikeEpigramResponseDto likeEpigramResponseDto = new LikeEpigramResponseDto();
            BeanUtils.copyProperties(epigram, likeEpigramResponseDto);
            likeEpigramResponseDto.setLiked(true);
            return likeEpigramResponseDto;
        }

        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("존재하지 않는 유저입니다."));
        Epigram epigramEntity = epigramRepository.findById(epigramId)
                .orElseThrow(() -> new EntityNotFoundException("해당 에피그램을 찾을 수 없습니다."));

        EpigramLike epigramLike = new EpigramLike();
        epigramLike.setUser(user);
        epigramLike.setEpigram(epigramEntity);

        epigramLikeRepository.save(epigramLike);

        LikeEpigramResponseDto likeEpigramResponseDto = new LikeEpigramResponseDto();
        BeanUtils.copyProperties(epigram, likeEpigramResponseDto);
        likeEpigramResponseDto.setLiked(true);

        return likeEpigramResponseDto;
    }

    @Transactional
    public LikeEpigramResponseDto deleteLikeEpigram(Long userId, Long epigramId) {
        EpigramResponseDto epigram = getEpigram(epigramId);
        EpigramLike epigramLike = epigramLikeRepository.findByUserIdAndEpigramId(userId, epigramId)
                .orElseThrow(() -> new RuntimeException("Like not found"));

        epigramLikeRepository.delete(epigramLike);

        LikeEpigramResponseDto likeEpigramResponseDto = new LikeEpigramResponseDto();
        BeanUtils.copyProperties(epigram, likeEpigramResponseDto);
        likeEpigramResponseDto.setLiked(false);

        return likeEpigramResponseDto;
    }

    public Epigram findEpigramById(Long id) {
        return epigramRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("해당 에피그램을 찾을 수 없습니다."));
    }
}