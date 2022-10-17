package com.barry.tennis.read.adapters.primary.springboot;

import com.barry.tennis.read.adapters.secondary.gateways.SqlMatchScoreQuery;
import com.barry.tennis.read.hexagone.gateways.MatchScoreQuery;
import com.barry.tennis.read.hexagone.usecases.RetrieveMatchScore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ReadDependenciesConfiguration {
    @Bean
    public RetrieveMatchScore retrieveMatchScore(MatchScoreQuery matchScoreQuery) {
        return new RetrieveMatchScore(matchScoreQuery);
    }

    @Bean
    public MatchScoreQuery matchScoreQuery() {
        return new SqlMatchScoreQuery();
    }
}
