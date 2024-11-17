package com.server.epigram.db.entity;

import static jakarta.persistence.CascadeType.MERGE;
import static jakarta.persistence.CascadeType.PERSIST;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
public class Epigram extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "reference_url")
    @Setter
    private String referenceUrl;

    @Column(name = "reference_title")
    @Setter
    private String referenceTitle;

    @Setter
    private String author;

    @Setter
    private String content;

    @Column(name = "comment_count")
    private Long commentCount;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany(mappedBy = "likedEpigrams")
    private Set<User> likedByUsers = new HashSet<>();

    @ManyToMany(cascade = {MERGE, PERSIST})
    @JoinTable(
            name = "epigram_tag",
            joinColumns = @JoinColumn(name = "epigram_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    @Setter
    private List<Tag> tags = new ArrayList<>();

}
