package com.barry.tennis.write.hexagon.usecases;

import com.barry.tennis.write.hexagon.gateways.repositories.MatchScoreRepository;
import com.barry.tennis.write.hexagon.models.DateTimeProvider;
import com.barry.tennis.write.hexagon.models.MatchScore;

import java.util.UUID;

public class UpdateMatchScore {

    private final MatchScoreRepository matchScoreRepository;
    private final DateTimeProvider dateTimeProvider;

    public UpdateMatchScore(MatchScoreRepository matchScoreRepository,
                            DateTimeProvider dateTimeProvider) {
        this.matchScoreRepository = matchScoreRepository;
        this.dateTimeProvider = dateTimeProvider;
    }

    public void handle(UUID matchId, String playerWinningPoint) {
        MatchScore matchScore = matchScoreRepository.find(matchId);
        MatchScore newMatchScore = matchScore.givePointTo(playerWinningPoint, dateTimeProvider);
        matchScoreRepository.save(matchId, newMatchScore);
    }

}
