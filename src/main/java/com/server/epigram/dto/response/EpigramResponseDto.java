package com.server.epigram.dto.response;

import com.server.epigram.dto.TagDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EpigramResponseDto {

    private Long id;
    private String referenceUrl;
    private String referenceTitle;
    private String author;
    private String content;
    private Long writerId;
    private List<TagDto> tags;
    private Long likeCount;

}
