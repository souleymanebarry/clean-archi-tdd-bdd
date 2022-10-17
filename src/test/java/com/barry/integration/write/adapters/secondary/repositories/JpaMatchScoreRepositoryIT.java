package com.barry.integration.write.adapters.secondary.repositories;


import com.barry.integration.DatabaseIT;
import com.barry.integration.read.adapters.secondary.TestConfiguration;
import com.barry.tennis.write.hexagon.gateways.repositories.MatchScoreRepository;
import com.barry.tennis.write.hexagon.models.MatchScore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

import static java.util.UUID.fromString;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@ContextConfiguration(classes = TestConfiguration.class)
@Transactional
public class JpaMatchScoreRepositoryIT extends DatabaseIT {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private MatchScoreRepository matchScoreRepository;
    private final UUID matchId = fromString("cb8156f3-3de4-4849-909b-b1ccd176a770");
    private final UUID matchScoreId = fromString("dd8156f3-3de4-4849-909b-b1ccd176a771");

    @BeforeEach
    void setup() {
        String score = "{\"Nadal\":\"15\",\"Federer\":\"30\"}";
        Query queryMatch = entityManager.createNativeQuery("" +
                "INSERT INTO test.matches (uuid, label) " +
                "VALUES (:matchId, 'Nadal - Federer')");
        queryMatch.setParameter("matchId", matchId);
        Query queryMatchScore = entityManager.createNativeQuery("" +
                "INSERT INTO test.match_scores (uuid, match_uuid, score, occurred_on) " +
                "VALUES (:matchScoreId, :matchId, :score, :occurredOn)");
        queryMatchScore.setParameter("matchId", matchId);
        queryMatchScore.setParameter("matchScoreId", matchScoreId);
        queryMatchScore.setParameter("score", score);
        queryMatchScore.setParameter("occurredOn", LocalDateTime.now());
        queryMatch.executeUpdate();
        queryMatchScore.executeUpdate();
    }

    @Test
    void shouldUpdateAMatchScoreOfAGivenMatchId() {
        MatchScore newMatchScore = new MatchScore(
                Map.of("Nadal", "15", "Federer", "40"),
                LocalDateTime.now()
        );
        matchScoreRepository.save(matchId, newMatchScore);
        assertThat(matchScoreRepository.find(matchId)).isEqualTo(newMatchScore);
    }

}
