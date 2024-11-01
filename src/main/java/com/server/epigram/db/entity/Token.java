package com.server.epigram.db.entity;

import com.server.epigram.global.common.TokenType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
public class Token extends BaseEntity{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "access_token", nullable = false)
  private String accessToken;

  @Column(name = "refresh_token", nullable = false)
  private String refreshToken;

  @Column(name = "token_type", nullable = false)
  private TokenType tokenType;

  @Column(name = "expires_at")
  private LocalDateTime expiresAt;

  private boolean revoked;  // 토근 만료 여부

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

}
