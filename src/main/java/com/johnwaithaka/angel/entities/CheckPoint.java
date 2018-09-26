package com.johnwaithaka.angel.entities;

import lombok.Data;

import javax.persistence.Id;
import java.io.File;

@Data
public class CheckPoint {

    @Id
    String id;
    File wordImage;
    String incompleteTestWord;

    public File getWordImage() {
        return wordImage;
    }

    public void setWordImage(File wordImage) {
        this.wordImage = wordImage;
    }

    public String getIncompleteTestWord() {
        return incompleteTestWord;
    }

    public void setIncompleteTestWord(String incompleteTestWord) {
        this.incompleteTestWord = incompleteTestWord;
    }
}
