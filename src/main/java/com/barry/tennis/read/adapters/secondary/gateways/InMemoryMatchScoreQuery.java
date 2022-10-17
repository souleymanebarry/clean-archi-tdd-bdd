package com.barry.tennis.read.adapters.secondary.gateways;

import com.barry.tennis.read.hexagone.gateways.MatchScoreQuery;
import com.barry.tennis.read.hexagone.usecases.MatchScoreViewModel;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class InMemoryMatchScoreQuery implements MatchScoreQuery {

    private Map<UUID, MatchScoreViewModel> matchScoreViewModels = new HashMap<>();

    @Override
    public MatchScoreViewModel retrieve(UUID matchId) {
        return matchScoreViewModels.get(matchId);
    }

    public void feedWith(Map<UUID, MatchScoreViewModel> matchScoreViewModels) {
        this.matchScoreViewModels = matchScoreViewModels;
    }
}
