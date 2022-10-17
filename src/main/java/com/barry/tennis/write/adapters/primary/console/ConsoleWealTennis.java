package com.barry.tennis.write.adapters.primary.console;

import com.barry.tennis.write.adapters.secondary.gateways.repositories.InMemoryMatchScoreRepository;
import com.barry.tennis.write.hexagon.models.MatchScore;
import com.barry.tennis.write.hexagon.models.RealDateTimeProvider;
import com.barry.tennis.write.hexagon.usecases.UpdateMatchScore;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static java.util.UUID.fromString;

public class ConsoleWealTennis {

    public static void main(String[] args) {
        UUID matchId = fromString("cb8156f3-3de4-4849-909b-b1ccd176a770");
        InMemoryMatchScoreRepository matchScoreRepository = new InMemoryMatchScoreRepository();
        matchScoreRepository.feedWith(
                Map.of(matchId,
                        List.of(new MatchScore(
                                Map.of("Nadal", "15", "Federer", "30"), LocalDateTime.now())
                        )
                ));
        new UpdateMatchScore(matchScoreRepository, new RealDateTimeProvider()).handle(matchId, "Federer");
        System.out.println(matchScoreRepository.scores(matchId));
    }

}
