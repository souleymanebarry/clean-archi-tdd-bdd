package com.barry.tennis.write.adapters.secondary.gateways.repositories.jpa;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "match_scores")
@Entity
public class JpaMatchScoreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID uuid;
    @ManyToOne(fetch = FetchType.EAGER)
    private JpaMatchEntity match;
    private String score;
    private LocalDateTime occurredOn;

    public JpaMatchScoreEntity() {
    }

    public void setMatch(JpaMatchEntity jpaMatchEntity) {
        match = jpaMatchEntity;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public void setOccurredOn(LocalDateTime occurredOn) {
        this.occurredOn = occurredOn;
    }

    public LocalDateTime getOccurredOn() {
        return occurredOn;
    }

    public String getScore() {
        return score;
    }
}
