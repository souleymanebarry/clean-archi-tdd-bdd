package com.barry.tennis.write.hexagon.gateways.repositories;

import com.barry.tennis.write.hexagon.models.MatchScore;

import java.util.UUID;

public interface MatchScoreRepository {
    void save(UUID matchId, MatchScore matchScore);
    MatchScore find(UUID matchId);
}
