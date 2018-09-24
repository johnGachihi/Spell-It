package com.johnwaithaka.angel.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

@Data
public class Level {

    @Id
    private String id;
    private List<Lesson> lessons;

    public String getId() {
        return id;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }
}
