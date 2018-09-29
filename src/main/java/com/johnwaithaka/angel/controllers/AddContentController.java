package com.johnwaithaka.angel.controllers;

import com.johnwaithaka.angel.DTOs.AdminDto;
import com.johnwaithaka.angel.entities.Lesson;
import com.johnwaithaka.angel.entities.Level;
import com.johnwaithaka.angel.entities.Word;
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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

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

    @PostMapping(value = "lesson-submit")
    @ResponseBody
    public Level lessonSubmission(
            @RequestParam String word,
            @RequestParam List<String> segments,
            @RequestParam MultipartFile wordImage,
            @RequestParam MultipartFile wordPhonetic,
            @RequestParam(required = false) String levelID
    ) {
        Level level;

        System.out.println("la: " + levelID);
        if (levelID != null && !levelID.isEmpty()){
            Optional<Level> oLevel = levelRepository.findById(levelID);
            level = oLevel.get();
        } else {
            String objectID = new ObjectId().toString();
            System.out.println(objectID);
            level = new Level();
            level.setId(objectID);
        }

        Word w = new Word(
                word,
                segments,
                multipartToFile(wordImage),
                multipartToFile(wordPhonetic)
        );

        level.addLesson(new Lesson(w));

        return levelRepository.save(level);
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

    private File multipartToFile(MultipartFile mf) {
        File f = new File("C:\\users\\john\\desktop\\spellit", mf.getOriginalFilename());
        try {
            FileOutputStream fos = new FileOutputStream(f);
            fos.write(mf.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return f;
    }
}
