package com.server.epigram.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TagDto {

    private Long id;

    @Setter
    private String name;

    public TagDto(String name) {
        this.name = name;
    }
}
