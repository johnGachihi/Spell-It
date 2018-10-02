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
    String imagePath;
    String phoneticPath;

    public Word() {
    }

    public Word(String text, List<String> wordSegments, String imagePath, String phoneticPath) {
        this.text = text;
        this.wordSegments = wordSegments;
        this.phoneticPath = phoneticPath;
        this.imagePath = imagePath;
    }

    public String getText() {
        return text;
    }

    public List<String> getWordSegments() {
        return wordSegments;
    }

    public String getImage() {
        return imagePath;
    }

    public String getPhonetic() {
        return phoneticPath;
    }
}
