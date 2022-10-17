package com.barry.tennis.read.hexagone.usecases;

import com.barry.tennis.read.hexagone.gateways.MatchScoreQuery;

import java.util.UUID;

public class RetrieveMatchScore {

    private final MatchScoreQuery matchScoreQuery;

    public RetrieveMatchScore(MatchScoreQuery matchScoreQuery) {
        this.matchScoreQuery = matchScoreQuery;
    }

    public MatchScoreViewModel handle(UUID matchId) {
        return matchScoreQuery.retrieve(matchId);
    }

}
