package com.barry.tennis.write.adapters.secondary.gateways.repositories.jpa;

import com.barry.tennis.write.hexagon.gateways.repositories.MatchScoreRepository;
import com.barry.tennis.write.hexagon.models.MatchScore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;
import java.util.UUID;

public class JpaMatchScoreRepository implements MatchScoreRepository {

    private final SpringJpaMatchScoreRepository matchScoreRepository;
    private final SpringJpaMatchRepository matchRepository;

    public JpaMatchScoreRepository(SpringJpaMatchScoreRepository matchScoreRepository,
                                   SpringJpaMatchRepository matchRepository) {
        this.matchScoreRepository = matchScoreRepository;
        this.matchRepository = matchRepository;
    }

    @Override
    public void save(UUID matchId, MatchScore matchScore) {
        matchRepository.findByUuid(matchId).ifPresent(jpaMatchEntity -> {
            try {
                JpaMatchScoreEntity jpaMatchScoreEntity = new JpaMatchScoreEntity();
                ObjectMapper mapper = new ObjectMapper();
                jpaMatchScoreEntity.setScore(mapper.writeValueAsString(matchScore.getPlayerScores()));
                jpaMatchScoreEntity.setOccurredOn(matchScore.getOccurredOn());
                jpaMatchEntity.addMatchScore(jpaMatchScoreEntity);
                matchScoreRepository.save(jpaMatchScoreEntity);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public MatchScore find(UUID matchId) {
        return matchScoreRepository.findByMatchUuidOrderByOccurredOnDesc(matchId).stream().findFirst().map(
                matchScore -> {
                    try {
                        ObjectMapper mapper = new ObjectMapper();
                        Map<String, String> score = mapper.readValue(matchScore.getScore(), Map.class);
                        return new MatchScore(score, matchScore.getOccurredOn());
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                        return null;
                    }
                }
        ).get();
    }
}
