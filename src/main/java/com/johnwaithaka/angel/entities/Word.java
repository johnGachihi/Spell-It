package com.johnwaithaka.angel.entities;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Word {
    @Id @GeneratedValue
    String id;
    String text;
//    MultipartFile phonetic;
//    MultipartFile image;
}
