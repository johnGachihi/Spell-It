package com.johnwaithaka.angel.DTOs;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class WordDto {
    @NotEmpty @NotNull
    private String word;
    @NotEmpty @NotNull
    private MultipartFile phonetic;
    @NotEmpty @NotNull
    private MultipartFile image;

    public String getWord() {
        return word;
    }
}
