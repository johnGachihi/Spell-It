package com.johnwaithaka.angel.entities;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Document
public class User {

    @Id
    String username;
    String password;
    String levelId;
    double aggregateScore;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLevelId() {
        return levelId;
    }

    public void setLevelId(String levelId) {
        this.levelId = levelId;
    }

    public double getAggregateScore() {
        return aggregateScore;
    }

    public void setAggregateScore(double aggregateScore) {
        this.aggregateScore = aggregateScore;
    }
}
