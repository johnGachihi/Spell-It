package com.johnwaithaka.angel.DTOs;

import lombok.Data;

import java.util.List;

@Data
public class LessonDTO {

    String text;
    List<String> wordSegments;
    String image;
    String phonetic;
    int lessonNumber;

    public LessonDTO(String text, List<String> wordSegments, String image, String phonetic, int lessonNumber) {
        this.text = text;
        this.wordSegments = wordSegments;
        this.image = image;
        this.phonetic = phonetic;
        this.lessonNumber = lessonNumber;
    }
}
