package com.server.epigram.db.repository;

import com.server.epigram.db.entity.EpigramLike;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EpigramLikeRepository extends JpaRepository<EpigramLike, Long> {

    boolean existsByUserIdAndEpigramId(Long userId, Long epigramId);

    Optional<EpigramLike> findByUserIdAndEpigramId(Long userId, Long epigramId);
}
