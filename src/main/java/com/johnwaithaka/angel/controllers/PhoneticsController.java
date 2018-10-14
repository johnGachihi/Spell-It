package com.johnwaithaka.angel.controllers;

import com.johnwaithaka.angel.DTOs.Response;
import com.johnwaithaka.angel.services.PhoneticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
public class PhoneticsController {

    @Autowired
    PhoneticService phoneticService;

    @RequestMapping("/phonetics/check-availability")
    @ResponseBody
    public List<String> getAbsentTexts(@RequestParam(value = "texts[]") List<String> texts){
        return phoneticService.getAllAbsentText(texts);
    }

    @RequestMapping("/phonetics/saveAllPhonetics")
    @ResponseBody
    public Response savePhonetics(@RequestParam(value = "files")List<MultipartFile> files){
        for(MultipartFile f : files)
            System.out.println(f.getOriginalFilename());

        phoneticService.saveAll(files);

        Response r = new Response();
        r.setError(false);
        return r;
    }
}
