package com.barry.tennis.read.hexagone.usecases;

import java.util.Map;
import java.util.Objects;

public class MatchScoreViewModel {
    Map<String, String> playersScore;

    public MatchScoreViewModel(Map<String, String> playersScore) {
        this.playersScore = playersScore;
    }

    public Map<String, String> getPlayersScore() {
        return playersScore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MatchScoreViewModel that = (MatchScoreViewModel) o;
        return Objects.equals(playersScore, that.playersScore);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playersScore);
    }

    @Override
    public String toString() {
        return "MatchScoreViewModel{" +
                "playersScore=" + playersScore +
                '}';
    }
}
