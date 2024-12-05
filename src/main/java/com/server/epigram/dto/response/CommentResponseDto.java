package com.server.epigram.dto.response;

import com.server.epigram.dto.WriterDto;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentResponseDto {
    private Long id;
    private String content;
    @Setter
    private boolean isPrivate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long epigramId;
    private WriterDto writer;
}
