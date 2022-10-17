package com.barry.tennis.read.adapters.primary.springboot.controllers;

import com.barry.tennis.read.hexagone.usecases.MatchScoreViewModel;
import com.barry.tennis.read.hexagone.usecases.RetrieveMatchScore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import static java.util.UUID.fromString;

@RestController
public class MatchScoreQueriesController {

    private final RetrieveMatchScore retrieveMatchScore;

    public MatchScoreQueriesController(RetrieveMatchScore retrieveMatchScore) {
        this.retrieveMatchScore = retrieveMatchScore;
    }

    @GetMapping("/matches/{matchId}/scores")
    public MatchScoreViewModel retrieveMatchScore(
            @PathVariable("matchId") String matchId
    ) {
        return retrieveMatchScore.handle(fromString(matchId));
    }
}
