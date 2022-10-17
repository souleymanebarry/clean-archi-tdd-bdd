package com.barry.unit.write.hexagon.usecases;


import com.barry.tennis.write.adapters.secondary.gateways.repositories.InMemoryMatchScoreRepository;
import com.barry.tennis.write.hexagon.models.DateTimeProvider;
import com.barry.tennis.write.hexagon.models.DeterministicDateTimeProvider;
import com.barry.tennis.write.hexagon.models.MatchScore;
import com.barry.tennis.write.hexagon.usecases.UpdateMatchScore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static java.util.UUID.fromString;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class UpdateMatchScoreTest {

    private final InMemoryMatchScoreRepository matchScoreRepository = new InMemoryMatchScoreRepository();
    private final DateTimeProvider dateTimeProvider = new DeterministicDateTimeProvider(
            LocalDateTime.of(2022, 1, 1, 14, 15, 3)
    );
    private final UpdateMatchScore updateMatchScore = new UpdateMatchScore(matchScoreRepository, dateTimeProvider);
    private final UUID matchId = fromString("cb8156f3-3de4-4849-909b-b1ccd176a770");

    @BeforeEach
    void setup() {
        matchScoreRepository.feedWith(
                Map.of(
                        matchId, List.of(
                                new MatchScore("Nadal", "Federer",
                                        LocalDateTime.of(2022, 1, 1, 14, 13, 3)))
                )
        );
    }

    @Test
    void shouldIncrementScoreOfThePlayerWinningTheCurrentPoint() {
        assertCurrentMatchScores(new WinningPoints("Nadal"),
                "Nadal", "15", "Federer", "0");
        assertCurrentMatchScores(new WinningPoints("Federer"),
                "Nadal", "0", "Federer", "15");
        assertCurrentMatchScores(new WinningPoints("Federer", "Federer"),
                "Nadal", "0", "Federer", "30");
        assertCurrentMatchScores(new WinningPoints("Federer", "Federer", "Federer"),
                "Nadal", "0", "Federer", "40");
        assertCurrentMatchScores(new WinningPoints("Federer", "Federer", "Federer", "Federer"),
                "Nadal", "0", "Federer", "Game");
        assertCurrentMatchScores(new WinningPoints("Federer", "Federer", "Federer", "Nadal", "Nadal", "Nadal", "Nadal"),
                "Nadal", "Avantage", "Federer", "40");
        assertCurrentMatchScores(new WinningPoints("Federer", "Federer", "Federer", "Nadal", "Nadal", "Nadal", "Federer"),
                "Nadal", "40", "Federer", "Avantage");
        assertCurrentMatchScores(new WinningPoints("Federer", "Federer", "Federer", "Nadal", "Nadal", "Nadal", "Nadal", "Federer"),
                "Nadal", "40", "Federer", "40");
        assertCurrentMatchScores(new WinningPoints("Federer", "Federer", "Federer", "Nadal", "Nadal", "Nadal", "Federer", "Federer"),
                "Nadal", "40", "Federer", "Game");

    }

    private void assertCurrentMatchScores(WinningPoints winningPoints,
                                          String player1, String score1,
                                          String player2, String score2) {
        matchScoreRepository.reset();
        matchScoreRepository.feedWith(Map.of(matchId, List.of(new MatchScore(player1, player2, dateTimeProvider.now()))));
        winningPoints.winningPlayers.forEach(playerWinningPoint ->
                updateMatchScore.handle(fromString("cb8156f3-3de4-4849-909b-b1ccd176a770"), playerWinningPoint));
        assertThat(matchScoreRepository.scores(matchId)
                .get(matchScoreRepository.scores(matchId).size() - 1)).isEqualTo(
                new MatchScore(Map.of(player1, score1, player2, score2), dateTimeProvider.now())
        );
    }

    private class WinningPoints {

        private final List<String> winningPlayers;

        public WinningPoints(String... winningPlayers) {
            this.winningPlayers = Arrays.asList(winningPlayers);
        }

    }


}
