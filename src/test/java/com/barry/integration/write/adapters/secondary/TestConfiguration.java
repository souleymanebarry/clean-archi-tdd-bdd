package com.barry.integration.write.adapters.secondary;


import com.barry.tennis.write.adapters.secondary.gateways.repositories.jpa.JpaMatchScoreRepository;
import com.barry.tennis.write.adapters.secondary.gateways.repositories.jpa.SpringJpaMatchRepository;
import com.barry.tennis.write.adapters.secondary.gateways.repositories.jpa.SpringJpaMatchScoreRepository;
import com.barry.tennis.write.hexagon.gateways.repositories.MatchScoreRepository;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan("com.barry.tennis.write.adapters.secondary.gateways.repositories")
@EnableJpaRepositories("com.barry.tennis.write.adapters.secondary.gateways.repositories")
public class TestConfiguration {

    @Bean
    public MatchScoreRepository matchScoreRepository(SpringJpaMatchScoreRepository matchScoreRepository,
                                                     SpringJpaMatchRepository matchRepository) {
        return new JpaMatchScoreRepository(matchScoreRepository, matchRepository);
    }

}
