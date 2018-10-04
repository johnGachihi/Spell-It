package com.johnwaithaka.angel.entities;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Document(collection = "users")
public class Angel {
    @Id
    String id;
    String username;
    String password;
    String levelId;
    double aggregateScore;
    Date regDate;
    List<String> roles;

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

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public List<String> getRoles(){ return this.roles; }

    public void addRole(String role){
        if (roles == null)
            roles = new ArrayList<>();

        roles.add(role);
    }
}
