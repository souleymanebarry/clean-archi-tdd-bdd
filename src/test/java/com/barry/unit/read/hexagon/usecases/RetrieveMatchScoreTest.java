package com.barry.unit.read.hexagon.usecases;


import com.barry.tennis.read.adapters.secondary.gateways.InMemoryMatchScoreQuery;
import com.barry.tennis.read.hexagone.usecases.MatchScoreViewModel;
import com.barry.tennis.read.hexagone.usecases.RetrieveMatchScore;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.UUID;

import static java.util.UUID.fromString;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class RetrieveMatchScoreTest {

    private final InMemoryMatchScoreQuery matchScoreQuery = new InMemoryMatchScoreQuery();
    private final UUID matchId = fromString("cb8156f3-3de4-4849-909b-b1ccd176a770");

    @Test
    void shouldRetrieveTheMatchScore() {
        MatchScoreViewModel matchScoreViewModel = new MatchScoreViewModel(
                Map.of("Nadal", "15", "Federer", "30")
        );
        matchScoreQuery.feedWith(Map.of(matchId, matchScoreViewModel));
        MatchScoreViewModel retrievedMatchScoreViewModel =
                new RetrieveMatchScore(matchScoreQuery).handle(fromString("cb8156f3-3de4-4849-909b-b1ccd176a770"));
        assertThat(retrievedMatchScoreViewModel).isEqualTo(matchScoreViewModel);
    }


}
