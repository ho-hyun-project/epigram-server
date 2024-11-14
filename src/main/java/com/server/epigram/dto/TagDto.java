package com.server.epigram.dto;

import com.server.epigram.db.entity.Tag;
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

    public Tag toEntity() {
        Tag tag = new Tag();
        tag.setName(this.name);
        return tag;
    }

    public static TagDto fromEntity(Tag tag) {
        return new TagDto(tag.getId(), tag.getName());
    }
}
