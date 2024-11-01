package com.server.epigram;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class EpigramApplication {

  public static void main(String[] args) {
    SpringApplication.run(EpigramApplication.class, args);
  }

}
