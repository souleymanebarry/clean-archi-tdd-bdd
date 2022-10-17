package com.barry.tennis.write.hexagon.models;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class MatchScore {

    public static final List<String> SCORE_LADDER = List.of("15", "30", "40", "Game");
    private final Map<String, String> playerScores = new HashMap<>();
    private final LocalDateTime occurredOn;

    public MatchScore(Map<String, String> playerScores, LocalDateTime occurredOn) {
        this.playerScores.putAll(playerScores);
        this.occurredOn = occurredOn;
    }

    public MatchScore(String player1, String player2, LocalDateTime occurredOn) {
        playerScores.put(player1, "0");
        playerScores.put(player2, "0");
        this.occurredOn = occurredOn;
    }

    public MatchScore givePointTo(String playerWinningPoint, DateTimeProvider dateTimeProvider) {
        MatchScore matchScore = new MatchScore(playerScores, dateTimeProvider.now());
        String currentPlayerWinningPointScore = playerScores.get(playerWinningPoint);
        String currentLosingPlayerPointScore = playerLoosingPointScore(playerWinningPoint);
        if (currentPlayerWinningPointScore.equals(currentLosingPlayerPointScore) && currentLosingPlayerPointScore.equals("40")) {
            matchScore.playerScores.put(playerWinningPoint, "Avantage");
        } else if (currentLosingPlayerPointScore.equals("Avantage")) {
            matchScore.playerScores.put(playerLoosingPoint(playerWinningPoint), "40");
        } else if (currentPlayerWinningPointScore.equals("Avantage")) {
            matchScore.playerScores.put(playerWinningPoint, "Game");
        } else {
            matchScore.playerScores.put(playerWinningPoint, SCORE_LADDER.get(SCORE_LADDER.indexOf(currentPlayerWinningPointScore) + 1));
        }
        return matchScore;
    }

    private String playerLoosingPointScore(String playerWinningPoint) {
        return playerScores.get(playerLoosingPoint(playerWinningPoint));
    }

    private String playerLoosingPoint(String playerWinningPoint) {
        var player = playerScores.keySet();
        player.removeIf(playerWinningPoint::equals);
        return player.iterator().next();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MatchScore that = (MatchScore) o;
        return Objects.equals(playerScores, that.playerScores) && Objects.equals(occurredOn, that.occurredOn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerScores, occurredOn);
    }

    public Map<String, String> getPlayerScores() {
        return playerScores;
    }

    public LocalDateTime getOccurredOn() {
        return occurredOn;
    }

    @Override
    public String toString() {
        return "MatchScore{" +
                "playerScores=" + playerScores +
                ", occurredOn=" + occurredOn +
                '}';
    }
}
