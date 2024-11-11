package com.server.epigram.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.server.epigram.db.entity.Epigram;
import com.server.epigram.db.entity.Tag;
import com.server.epigram.db.repository.EpigramRepository;
import com.server.epigram.db.repository.TagRepository;
import com.server.epigram.dto.TagDto;
import com.server.epigram.dto.request.EpigramRequestDto;
import com.server.epigram.dto.response.EpigramResponseDto;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class EpigramServiceTest {

    @Mock
    private EpigramRepository epigramRepository;

    @Mock
    private TagRepository tagRepository;

    @InjectMocks
    private EpigramService epigramService;

    private EpigramRequestDto epigramRequestDto;

    @BeforeEach
    void setUp() {
        TagDto tagDto1 = new TagDto(1L, "Tag1");
        TagDto tagDto2 = new TagDto(2L, "Tag2");

        epigramRequestDto = new EpigramRequestDto(
                "Reference URL",
                "Reference Title",
                "Author",
                "Content",
                Arrays.asList(tagDto1, tagDto2)
        );
    }

    @Test
    void testCreateEpigram() {
        // Mock TagRepository behavior
        Tag tag1 = new Tag();
        tag1.setName("Tag1");

        Tag tag2 = new Tag();
        tag2.setName("Tag2");

        when(tagRepository.save(any(Tag.class))).thenReturn(tag1).thenReturn(tag2);
        when(epigramRepository.save(any(Epigram.class))).thenReturn(new Epigram());

        // Call the service method
        EpigramResponseDto responseDto = epigramService.createEpigram(epigramRequestDto);

        // Verify that methods were called
        verify(tagRepository, times(2)).save(any(Tag.class));
        verify(epigramRepository, times(1)).save(any(Epigram.class));

        // Verify the response
        assertNotNull(responseDto);
        assertEquals("Reference Title", responseDto.getReferenceTitle());
        assertEquals("Reference URL", responseDto.getReferenceUrl());
        assertEquals("Author", responseDto.getAuthor());
        assertEquals("Content", responseDto.getContent());
        assertEquals(2, responseDto.getTags().size());  // 두 개의 태그가 있어야 합니다.
        assertEquals(0L, responseDto.getLikeCount());  // likeCount는 0L로 설정됨
    }

    @Test
    void testCreateEpigramWithEmptyTags() {
        // 빈 태그 리스트
        epigramRequestDto = new EpigramRequestDto(
                "Reference URL",
                "Reference Title",
                "Author",
                "Content",
                List.of()  // 빈 태그 리스트
        );

        when(epigramRepository.save(any(Epigram.class))).thenReturn(new Epigram());

        // Call the service method
        EpigramResponseDto responseDto = epigramService.createEpigram(epigramRequestDto);

        // Verify that methods were called
        verify(tagRepository, never()).save(any(Tag.class));  // 태그 저장은 호출되지 않아야 함
        verify(epigramRepository, times(1)).save(any(Epigram.class));

        // Verify the response
        assertNotNull(responseDto);
        assertEquals("Reference Title", responseDto.getReferenceTitle());
        assertEquals("Reference URL", responseDto.getReferenceUrl());
        assertEquals("Author", responseDto.getAuthor());
        assertEquals("Content", responseDto.getContent());
        assertTrue(responseDto.getTags().isEmpty());  // 빈 태그 리스트여야 합니다.
    }
}
