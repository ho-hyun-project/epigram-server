package com.server.epigram.dto.response.epigram;

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
public class TotalEpigramResponseDto {
    private Long totalCount;
    private Long nextCursor;
    private List<EpigramResponseDto> epigramResponseDtos;
}
