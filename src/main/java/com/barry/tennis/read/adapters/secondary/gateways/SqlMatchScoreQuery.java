package com.barry.tennis.read.adapters.secondary.gateways;

import com.barry.tennis.read.hexagone.gateways.MatchScoreQuery;
import com.barry.tennis.read.hexagone.usecases.MatchScoreViewModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Map;
import java.util.UUID;

public class SqlMatchScoreQuery implements MatchScoreQuery {

    @PersistenceContext
	private EntityManager entityManager;

	@Override
	public MatchScoreViewModel retrieve(UUID matchId) {
		Query query = entityManager.createNativeQuery("select ms.score from matches m " +
						"inner join match_scores ms on m.uuid = ms.match_uuid " +
						"where m.uuid = :uuid order by ms.occurred_on desc LIMIT 1 ")
				.setParameter("uuid", matchId);
		Object rawResults = query.getSingleResult();
		ObjectMapper mapper = new ObjectMapper();
		Map<String, String> score = null;
		try {
			score = mapper.readValue((String)rawResults, Map.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return new MatchScoreViewModel(score);
	}
}
