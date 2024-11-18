package com.server.epigram.db.entity;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.CascadeType.MERGE;
import static jakarta.persistence.CascadeType.PERSIST;

import com.server.epigram.common.OauthType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(nullable = false)
    private String email;

    @Setter
    @Column(nullable = false)
    private String password;

    @Setter
    @Column(name = "oauth_id")
    private String oauthId;

    @Setter
    @Column(name = "oauth_provider")
    private OauthType oauthProvider;

    @Setter
    @Column(nullable = false)
    private String nickName;

    @Setter
    private String image;

    @OneToMany(mappedBy = "user", cascade = ALL, orphanRemoval = true)
    private List<Epigram> epigrams = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = ALL, orphanRemoval = true)
    private List<Emotion> emotions = new ArrayList<>();

    @ManyToMany(cascade = {MERGE, PERSIST})
    @JoinTable(
            name = "epigram_like",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "epigram_id")
    )
    private Set<Epigram> likedEpigrams = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = ALL, orphanRemoval = true)
    private List<Token> tokens = new ArrayList<>();

}
