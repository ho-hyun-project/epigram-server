package com.server.epigram.db.entity;

import com.server.epigram.global.common.EmotionType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Emotion extends BaseEntity{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private EmotionType emotion;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

}
