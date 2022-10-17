package com.barry.tennis.write.adapters.primary.springboot;

import com.barry.tennis.write.adapters.secondary.gateways.repositories.jpa.JpaMatchScoreRepository;
import com.barry.tennis.write.adapters.secondary.gateways.repositories.jpa.SpringJpaMatchRepository;
import com.barry.tennis.write.adapters.secondary.gateways.repositories.jpa.SpringJpaMatchScoreRepository;
import com.barry.tennis.write.hexagon.gateways.repositories.MatchScoreRepository;
import com.barry.tennis.write.hexagon.models.RealDateTimeProvider;
import com.barry.tennis.write.hexagon.usecases.UpdateMatchScore;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan("com.barry.tennis.write.adapters.secondary.gateways.repositories")
@EnableJpaRepositories("com.barry.tennis.write.adapters.secondary.gateways.repositories")
public class WriteDependenciesConfiguration {

    @Bean
    public UpdateMatchScore updateMatchScore(MatchScoreRepository matchScoreRepository) {
        return new UpdateMatchScore(matchScoreRepository, new RealDateTimeProvider());
    }

    @Bean
    public MatchScoreRepository matchScoreRepository(SpringJpaMatchScoreRepository springJpaMatchScoreRepository,
                                                     SpringJpaMatchRepository springJpaMatchRepository) {
        // UNCOMMENT THAT TO ENABLE IN MEMORY REPOSITORY
        /*var matchScoreRepository = new InMemoryMatchScoreRepository();
        matchScoreRepository.feedWith(Map.of(fromString("cb8156f3-3de4-4849-909b-b1ccd176a770"),
                List.of(new MatchScore(Map.of("Nadal", "15", "Federer", "30"), LocalDateTime.now())
        )));
        return matchScoreRepository;*/
        return new JpaMatchScoreRepository(springJpaMatchScoreRepository, springJpaMatchRepository);
    }

}
