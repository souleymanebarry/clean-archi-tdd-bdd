package com.barry.tennis.write.adapters.secondary.gateways.repositories.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SpringJpaMatchScoreRepository extends JpaRepository<JpaMatchScoreEntity, UUID> {

    List<JpaMatchScoreEntity> findByMatchUuidOrderByOccurredOnDesc(UUID matchId);

}
