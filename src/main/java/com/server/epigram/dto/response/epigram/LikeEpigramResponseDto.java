package com.server.epigram.dto.response.epigram;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LikeEpigramResponseDto extends EpigramResponseDto {
    private boolean isLiked;
}
