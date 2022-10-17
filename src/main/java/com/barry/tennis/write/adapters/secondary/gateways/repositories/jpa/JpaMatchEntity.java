package com.barry.tennis.write.adapters.secondary.gateways.repositories.jpa;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;
import java.util.UUID;

@Table(name = "matches")
@Entity
public class JpaMatchEntity {

    @Id
    private UUID uuid;
    @OneToMany(mappedBy = "match")
    private List<JpaMatchScoreEntity> matchScores;

    public JpaMatchEntity() {
    }

    public void addMatchScore(JpaMatchScoreEntity matchScore) {
        matchScores.add(matchScore);
        matchScore.setMatch(this);
    }

    public UUID getUuid() {
        return uuid;
    }
}
