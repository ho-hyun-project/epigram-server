package com.server.epigram.db.repository;

import com.server.epigram.db.entity.Epigram;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EpigramRepository extends JpaRepository<Epigram, Long> {

    @Query(value = "SELECT * FROM \"epigram\" ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Optional<Epigram> findRandomEpigram();

}
