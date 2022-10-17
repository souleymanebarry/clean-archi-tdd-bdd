package com.barry.tennis.read.hexagone.gateways;

import com.barry.tennis.read.hexagone.usecases.MatchScoreViewModel;

import java.util.UUID;

public interface MatchScoreQuery {
    MatchScoreViewModel retrieve(UUID matchId);
}
