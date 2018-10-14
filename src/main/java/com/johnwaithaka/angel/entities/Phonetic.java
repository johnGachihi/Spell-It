package com.johnwaithaka.angel.entities;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Data
@Document
public class Phonetic {
    @Id
    private String id;
    private String text;
    private String audioFileName;

    public Phonetic(){}

    public Phonetic(String text, String audioFileName) {
        this.text = text;
        this.audioFileName = audioFileName;
    }
}
