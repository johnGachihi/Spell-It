package com.johnwaithaka.angel.DTOs;

import com.johnwaithaka.angel.entities.Lesson;

import java.util.List;

public class LevelDTO {
    private String id;
    private List<Lesson> lessons;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }
}
