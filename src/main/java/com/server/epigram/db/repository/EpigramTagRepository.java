package com.server.epigram.db.repository;

import com.server.epigram.db.entity.EpigramTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EpigramTagRepository extends JpaRepository<EpigramTag, Long> {

}
