package com.server.epigram.db.entity;

import static jakarta.persistence.CascadeType.ALL;

import com.server.epigram.global.common.OauthType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User extends BaseEntity{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String email;

  @Column(nullable = false)
  private String password;

  @Column(name = "oauth_id")
  private String oauthId;

  @Column(name = "oauth_provider")
  private OauthType oauthProvider;

  @Column(nullable = false)
  private String nickName;

  private String image;

  @OneToMany(mappedBy = "user", cascade = ALL, orphanRemoval = true)
  private List<Epigram> epigrams = new ArrayList<>();

  @OneToMany(mappedBy = "user", cascade = ALL, orphanRemoval = true)
  private List<Comment> comments = new ArrayList<>();

  @OneToMany(mappedBy = "user", cascade = ALL, orphanRemoval = true)
  private List<Emotion> emotions = new ArrayList<>();

  @OneToMany(mappedBy = "user", cascade = ALL, orphanRemoval = true)
  private List<EpigramLike> epigramLikes = new ArrayList<>();

  @OneToMany(mappedBy = "user", cascade = ALL, orphanRemoval = true)
  private List<Token> tokens = new ArrayList<>();

}
