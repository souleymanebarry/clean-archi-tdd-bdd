package com.barry.tennis.write.adapters.primary.springboot.controllers;

import com.barry.tennis.write.hexagon.usecases.UpdateMatchScore;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import static java.util.UUID.fromString;

@RestController
public class MatchScoreWritesController {

    private final UpdateMatchScore updateMatchScore;

    public MatchScoreWritesController(UpdateMatchScore updateMatchScore) {
        this.updateMatchScore = updateMatchScore;
    }

    @PostMapping("/matches/{matchId}/scores")
    public Map<String, Object> updateMatchScore(
            @PathVariable("matchId") String matchId,
            @RequestBody UpdateMatchScoreParams updateMatchScoreParams
    ) {
        updateMatchScore.handle(fromString(matchId), updateMatchScoreParams.playerWinningPoint());
        Map<String, Object> json = new HashMap<>();
        json.put("data", null);
        return json;
    }

}

record UpdateMatchScoreParams(String playerWinningPoint) {
}
