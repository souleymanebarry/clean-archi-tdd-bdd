package com.barry.integration.read.adapters.secondary;


import com.barry.tennis.read.adapters.secondary.gateways.SqlMatchScoreQuery;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
public class TestConfiguration {

    @Bean
    public SqlMatchScoreQuery matchScoreQuery() {
        return new SqlMatchScoreQuery();
    }

}
