package com.barry.tennis.read.adapters.secondary.gateways;

import com.barry.tennis.read.hexagone.gateways.MatchScoreQuery;
import com.barry.tennis.read.hexagone.usecases.MatchScoreViewModel;
import com.barry.tennis.write.adapters.secondary.gateways.repositories.InMemoryMatchScoreRepository;

import java.util.UUID;

public class FakeMatchScoreQuery implements MatchScoreQuery {


    private final InMemoryMatchScoreRepository matchScoreRepository;

    public FakeMatchScoreQuery(InMemoryMatchScoreRepository matchScoreRepository) {
        this.matchScoreRepository = matchScoreRepository;
    }

    @Override
    public MatchScoreViewModel retrieve(UUID matchId) {
        return new MatchScoreViewModel(matchScoreRepository.find(matchId).getPlayerScores());
    }
}
