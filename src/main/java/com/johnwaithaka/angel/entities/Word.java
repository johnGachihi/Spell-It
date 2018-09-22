package com.johnwaithaka.angel.entities;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.File;

@Data
public class Word {
    @Id @GeneratedValue
    String id;
    String text;
    String[] wordSegments;
    File phonetic;
    File image;
}
