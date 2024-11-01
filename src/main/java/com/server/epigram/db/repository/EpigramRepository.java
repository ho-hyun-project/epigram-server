package com.server.epigram.db.repository;

import com.server.epigram.db.entity.Epigram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EpigramRepository extends JpaRepository<Epigram, Long> {

}
