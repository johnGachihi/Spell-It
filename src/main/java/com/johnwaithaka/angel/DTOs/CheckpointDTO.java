package com.johnwaithaka.angel.DTOs;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public class CheckpointDTO {
    MultipartFile checkpointWordImage;
    String quizWord;
    String incompleteQuizWord;
    String levelId;

    public CheckpointDTO(MultipartFile checkpointWordImage, String quizWord, String incompleteQuizWord, String levelId) {
        this.checkpointWordImage = checkpointWordImage;
        this.quizWord = quizWord;
        this.incompleteQuizWord = incompleteQuizWord;
        this.levelId = levelId;
    }

    public MultipartFile getCheckpointWordImage() {
        return checkpointWordImage;
    }

    public void setCheckpointWordImage(MultipartFile checkpointWordImage) {
        this.checkpointWordImage = checkpointWordImage;
    }

    public String getQuizWord() {
        return quizWord;
    }

    public void setQuizWord(String quizWord) {
        this.quizWord = quizWord;
    }

    public String getIncompleteQuizWord() {
        return incompleteQuizWord;
    }

    public void setIncompleteQuizWord(String incompleteQuizWord) {
        this.incompleteQuizWord = incompleteQuizWord;
    }

    public String getLevelId() {
        return levelId;
    }

    public void setLevelId(String levelId) {
        this.levelId = levelId;
    }
}
