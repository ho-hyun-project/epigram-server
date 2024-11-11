package com.server.epigram.dto.request;

import com.server.epigram.dto.TagDto;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class EpigramRequestDto {

    private String referenceUrl;
    private String referenceTitle;
    private String author;
    private String content;
    private List<TagDto> tags;

}
