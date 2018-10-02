package com.johnwaithaka.angel.entities;

import lombok.Data;

import javax.persistence.Id;
import java.io.File;

@Data
public class CheckPoint {

    @Id
    String id;
    String wordImagePath;
    String testWord;
    String incompleteTestWord;

    public CheckPoint() {
    }

    public CheckPoint(String wordImagePath, String testWord, String incompleteTestWord) {
        this.wordImagePath = wordImagePath;
        this.testWord = testWord;
        this.incompleteTestWord = incompleteTestWord;
    }

    public String getWordImagePath() {
        return wordImagePath;
    }

    public void setWordImagePath(String wordImagePath) {
        this.wordImagePath = wordImagePath;
    }

    public String getTestWord() {
        return testWord;
    }

    public void setTestWord(String testWord) {
        this.testWord = testWord;
    }

    public String getIncompleteTestWord() {
        return incompleteTestWord;
    }

    public void setIncompleteTestWord(String incompleteTestWord) {
        this.incompleteTestWord = incompleteTestWord;
    }
}
