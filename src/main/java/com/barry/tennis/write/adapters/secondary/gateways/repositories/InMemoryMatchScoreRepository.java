package com.barry.tennis.write.adapters.secondary.gateways.repositories;

import com.barry.tennis.write.hexagon.gateways.repositories.MatchScoreRepository;
import com.barry.tennis.write.hexagon.models.MatchScore;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class InMemoryMatchScoreRepository implements MatchScoreRepository {

    private final Map<UUID, List<MatchScore>> scores = new HashMap<>();

    public void save(UUID matchId, MatchScore matchScore) {
        scores.put(matchId, List.of(matchScore));
    }

    @Override
    public MatchScore find(UUID matchId) {
        return scores.get(matchId).get(scores.size() - 1);
    }

    public List<MatchScore> scores(UUID matchId) {
        return scores.get(matchId);
    }

    public void feedWith(Map<UUID, List<MatchScore>> scores) {
        this.scores.putAll(scores);
    }

    public void reset() {
        scores.clear();
    }
}
