package com.johnwaithaka.angel.controllers;

import com.johnwaithaka.angel.DTOs.WordDto;
import com.johnwaithaka.angel.entities.Word;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

@Controller
public class AddContentController {

    @RequestMapping(value = "new-content")
    @ResponseStatus(value = HttpStatus.OK)
    public void receiveWord(
            @RequestParam(value = "word") String word,
            @RequestParam(value = "phonetic")MultipartFile phonetic)
    {
        System.out.println("Word inserted: " + word);
        System.out.println("File name: " + phonetic.getOriginalFilename());
        System.out.println("Content type: " + phonetic.getContentType());
        System.out.println();
    }

    @Bean
    public StandardServletMultipartResolver multipartResolver() {
        return new StandardServletMultipartResolver();
    }
}
