package com.johnwaithaka.angel.entities;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Document
public class Lesson {

    @Id
    private String id;
    private Word word;

    private Lesson(){}

    public Lesson(Word word) {
        this.word = word;
    }

}
