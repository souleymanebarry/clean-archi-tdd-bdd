package com.barry.integration.read.adapters.secondary;


import com.barry.integration.DatabaseIT;
import com.barry.tennis.read.hexagone.gateways.MatchScoreQuery;
import com.barry.tennis.read.hexagone.usecases.MatchScoreViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

import static java.util.UUID.fromString;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@ContextConfiguration(classes = TestConfiguration.class)
public class SqlMatchScoreQueryIT extends DatabaseIT {

	@Autowired
	private MatchScoreQuery sqlMatchScoreQuery;

	@Autowired
	private EntityManager entityManager;

	private final UUID matchId = fromString("cb8156f3-3de4-4849-909b-b1ccd176a770");

	@BeforeEach
	void setup() {
		Query queryMatch = entityManager.createNativeQuery("" +
				"INSERT INTO test.matches (uuid, label) " +
				"VALUES (:matchId, 'Nadal - Federer')");
		queryMatch.setParameter("matchId", matchId);
		queryMatch.executeUpdate();
	}

	@Test
	void shouldRetrieveTheLastMatchScoreGivenASpecificMatch() {
		UUID matchScoreId1 = fromString("dd8156f3-3de4-4849-909b-b1ccd176a771");
		UUID matchScoreId2 = fromString("dd8156f3-3de4-4849-909b-b1ccd176a771");
		String score1 = "{\"Nadal\": \"15\", \"Federer\": \"30\"}";
		String score2 = "{\"Nadal\": \"30\", \"Federer\": \"30\"}";
		//store the first score first to force using order by occurred_on desc on the query
		storeAScore(
				matchScoreId1,
				score1,
				LocalDateTime.of(2022, 2, 3, 14, 15, 3));
		storeAScore(
				matchScoreId2,
				score2,
				LocalDateTime.of(2022, 2, 3, 14, 16, 3));
		MatchScoreViewModel retrievedMatchScoreViewModel = sqlMatchScoreQuery.retrieve(matchId);
		assertThat(retrievedMatchScoreViewModel).isEqualTo(new MatchScoreViewModel(
				Map.of("Nadal", "30", "Federer", "30")));
	}

	private void storeAScore(UUID matchScoreId, String score, LocalDateTime occurredOn) {
		Query queryMatchScore = entityManager.createNativeQuery("" +
				"INSERT INTO test.match_scores (uuid, match_uuid, score, occurred_on) " +
				"VALUES (:matchScoreId, :matchId, :score, :occurred_on)");
		queryMatchScore.setParameter("matchId", matchId);
		queryMatchScore.setParameter("matchScoreId", matchScoreId);
		queryMatchScore.setParameter("score", score);
		queryMatchScore.setParameter("occurred_on", occurredOn);
		queryMatchScore.executeUpdate();
	}

}
