package com.johnwaithaka.angel.controllers;

import com.johnwaithaka.angel.DTOs.AdminDto;
import com.johnwaithaka.angel.entities.Level;
import com.johnwaithaka.angel.repositories.LevelRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

import java.io.File;
import java.util.List;

@Controller
public class AddContentController {

    @Autowired
    LevelRepository levelRepository;

    @RequestMapping("/edit-content")
    public String addContent(
            Model model,
            @RequestParam(value="getLevel", required=false)String levelId
    ){
        AdminDto adminDto = new AdminDto();
        model.addAttribute("newAdmin", adminDto);

        int presentLevels = getNumberOfLevels();
        /*If levels are greater than a certain number restrict*/
        if(presentLevels > 14){}

        model.addAttribute("levelAndNumber", "Level " + presentLevels+1);

        if(levelId == null){
            model.addAttribute("level", new Level());
        } else {
            Level l = getLevel(levels(levelRepository), levelId);
            model.addAttribute("level", l);
        }

        return "edit-content";
    }

    @RequestMapping(value = "edit-lesson")
    public String editLessonPage(@RequestParam int lessonNumber, Model model){
        model.addAttribute("lessonNumber", "Level " + lessonNumber);
        return "edit-lesson";
    }

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

    @RequestMapping(value = "lesson-submit")
    public void lessonSubmission(
            @RequestParam String word,
            @RequestParam List<String> segments,
            @RequestParam MultipartFile wordImage,
            @RequestParam MultipartFile wordPhonetic,
            @RequestParam(required = false) String levelID
    ) {

        //Left of here. Consider re-structuring this if statement.
        if (levelID == null) {
            ObjectId oID = new ObjectId();
            String objectID = oID.toString();
            System.out.println(objectID);
            Level level = new Level();
            level.setId(objectID);
            level.getLessons().add(wordImage.t);
            new File()
        }
    }


    @Bean
    public StandardServletMultipartResolver multipartResolver() {
        return new StandardServletMultipartResolver();
    }

    private Level getLevel(List<Level> levels, String id){
        for(Level level : levels){
            if(level.getId().equals(id)){
                return level;
            }
        }
        return null;
    }


    private List<Level> levels(LevelRepository levelRepository){
        return levelRepository.findAll();

    }

    private int getNumberOfLevels(){
        return (int)levelRepository.count();
    }
}
