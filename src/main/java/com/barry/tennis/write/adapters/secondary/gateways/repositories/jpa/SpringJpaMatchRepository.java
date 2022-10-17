package com.barry.tennis.write.adapters.secondary.gateways.repositories.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SpringJpaMatchRepository extends JpaRepository<JpaMatchEntity, UUID> {

    Optional<JpaMatchEntity> findByUuid(UUID matchId);

}
