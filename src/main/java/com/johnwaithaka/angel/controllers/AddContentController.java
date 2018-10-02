package com.johnwaithaka.angel.controllers;

import com.johnwaithaka.angel.DTOs.AdminDto;
import com.johnwaithaka.angel.DTOs.CheckpointDTO;
import com.johnwaithaka.angel.DTOs.LessonDTO;
import com.johnwaithaka.angel.entities.CheckPoint;
import com.johnwaithaka.angel.entities.Lesson;
import com.johnwaithaka.angel.entities.Level;
import com.johnwaithaka.angel.entities.Word;
import com.johnwaithaka.angel.models.MenuItem;
import com.johnwaithaka.angel.repositories.LevelRepository;
import com.johnwaithaka.angel.services.FileService;
import com.johnwaithaka.angel.services.LevelsService;
import jdk.internal.util.xml.impl.Input;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

import java.io.*;
import java.util.List;
import java.util.Optional;

@Controller
public class AddContentController {

    @Autowired
    LevelsService levelsService;
    @Autowired
    FileService fileService;

    @RequestMapping("/edit-content")
    public String addContent(
            Model model,
            @RequestParam(required=false)String levelId
    ){
        AdminDto adminDto = new AdminDto();
        model.addAttribute("newAdmin", adminDto);

        int presentLevels = levelsService.countLevels();
        /*If levels are greater than a certain number restrict*/
        if(presentLevels > 14){}

//        model.addAttribute("levelAndNumber", "Level " + presentLevels+1);

        List<MenuItem> menuItems = MenuItem.levelsToMenuItems(
                levelsService.findAllLevels()
        );
        model.addAttribute("menuItems", menuItems);

        if(levelId == null){
            String id = new ObjectId().toString();
            Level l = new Level();
            l.setId(id);
            l.setLevelNo(presentLevels+1);
            model.addAttribute("level", l);
        } else {
            Level l = levelsService.findById(levelId).orElse(null);
            model.addAttribute("level", l);
        }

        return "edit-content";
    }

    @RequestMapping(value = "edit-lesson")
    public String editLessonPage(@RequestParam int lessonNumber, Model model){
        model.addAttribute("lessonNumber", "Level " + lessonNumber);
        return "edit-lesson";
    }

    @PostMapping(value = "lesson-submit")
    @ResponseBody
    public LessonDTO lessonSubmission(
            @RequestParam String word,
            @RequestParam List<String> segments,
            @RequestParam MultipartFile wordImage,
            @RequestParam MultipartFile wordPhonetic,
            @RequestParam(required = false) String levelID,
            @RequestParam int levelNo
    ) {
        Level level1;

        Optional<Level> o = levelsService.findById(levelID);
        if(!o.isPresent()){
            level1 = new Level();
            level1.setId(levelID);
        } else {
            level1 = o.get();
        }

        Word w = new Word(
                word,
                segments,
                fileService.multipartToFile(wordImage).getName(),
                fileService.multipartToFile(wordPhonetic).getName()
        );

        Lesson lesson = new Lesson(w);
//        Level level = new Level();
//        level.setId(levelID);
        level1.addLesson(lesson);
        level1.setLevelNo(levelNo);

        levelsService.save(level1);

        return new LessonDTO(
                w.getText(),
                w.getWordSegments(),
                w.getImage(),
                w.getPhonetic(),
                level1.getLessons().size()
        );
    }

    @RequestMapping(value = "checkpoint-content")
    @ResponseBody
    public Level getCheckpointContent(
            @RequestParam MultipartFile checkpointWordImage,
            @RequestParam String quizWord,
            @RequestParam String incompleteQuizWord,
            @RequestParam String levelId,
            @RequestParam int levelNo
    ) {
        Level l = null;

        //This is duplicate code.
        //Should be in a service class!!!
        Optional<Level> o = levelsService.findById(levelId);
        if(o.isPresent()){
            l = o.get();
        } else {
            l = new Level();
            l.setId(levelId);
        }

        l.setCheckPoint(new CheckPoint(
                fileService.multipartToFile(checkpointWordImage).getName(),
                quizWord,
                incompleteQuizWord
        ));
        l.setLevelNo(levelNo);

        return levelsService.save(l);
    }

    @RequestMapping(value = "content")
    @ResponseBody
    public FileSystemResource getFile(@RequestParam String imageName){
        File f = new File("content/" + imageName);
        return new FileSystemResource(f);
    }

//    @RequestMapping(value="w-image")
//    public byte[] serveImage(){
//
//    }

    @Bean
    public StandardServletMultipartResolver multipartResolver() {
        return new StandardServletMultipartResolver();
    }

}
