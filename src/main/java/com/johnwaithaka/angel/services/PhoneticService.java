package com.johnwaithaka.angel.services;

import com.johnwaithaka.angel.entities.Phonetic;
import com.johnwaithaka.angel.repositories.PhoneticRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


@Service
//@EnableCaching
public class PhoneticService {

    private PhoneticRepository phoneticRepository;
    private FileService fileService;

    @Autowired
    public PhoneticService(
            PhoneticRepository phoneticRepository,
            FileService fileService
    ){
        this.phoneticRepository = phoneticRepository;
        this.fileService = fileService;
    }

    public List<String> getAllAbsentText(List<String> texts){
        List<String> absentTexts = new ArrayList<>();
        for (String t : texts){
            if(!existsByText(t))
                absentTexts.add(t);
        }
        return absentTexts;
    }

//    @Cacheable
    public boolean existsByText(String text){
        return phoneticRepository.existsByText(text);
    }

    public void saveAll(List<MultipartFile> files) {
        List<Phonetic> phonetics = new ArrayList<>();
        for (MultipartFile f : files) {
            phonetics.add(multipartToPhonetic(f));
        }
        phoneticRepository.saveAll(phonetics);
    }

    private Phonetic multipartToPhonetic(MultipartFile f) {
        String text = f.getOriginalFilename().split("\\.")[0];
        File audioFile = fileService.multipartToFile(f);
        String fileName = audioFile.getName();

        return new Phonetic(text, fileName);
    }
}
