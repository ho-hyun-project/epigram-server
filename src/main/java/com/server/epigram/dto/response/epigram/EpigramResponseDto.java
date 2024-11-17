package com.server.epigram.dto.response.epigram;

import com.server.epigram.dto.TagDto;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
