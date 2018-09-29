package com.johnwaithaka.angel.entities;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.File;
import java.util.List;

@Data
public class Word {
    @Id @GeneratedValue
    String id;
    String text;
    List<String> wordSegments;
    File image;
    File phonetic;

    public Word() {
    }

    public Word(String text, List<String> wordSegments, File phonetic, File image) {
        this.text = text;
        this.wordSegments = wordSegments;
        this.phonetic = phonetic;
        this.image = image;
    }
}
