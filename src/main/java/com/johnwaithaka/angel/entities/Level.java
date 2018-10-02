package com.johnwaithaka.angel.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

@Data
public class Level {

    @Id
    private String id;
    private List<Lesson> lessons;
    private CheckPoint checkPoint;
    private Reward reward;
    private int levelNo;

    public String getId() {
        return id;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }

    public CheckPoint getCheckPoint() {
        return checkPoint;
    }

    public void setCheckPoint(CheckPoint checkPoint) {
        this.checkPoint = checkPoint;
    }


    public Reward getReward() {
        return reward;
    }

    public void setReward(Reward reward) {
        this.reward = reward;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void addLesson(Lesson l){
        if (lessons == null){
            lessons = new ArrayList<>();
        }
        lessons.add(l);
    }

    public int getLevelNo() {
        return levelNo;
    }

    public void setLevelNo(int levelNo) {
        this.levelNo = levelNo;
    }
}
