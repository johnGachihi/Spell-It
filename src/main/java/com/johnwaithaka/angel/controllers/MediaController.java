package com.johnwaithaka.angel.controllers;

import com.johnwaithaka.angel.services.FileService;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

@Controller
public class MediaController {

    @RequestMapping("/media-content")
    @ResponseBody
    public byte[] serveMedia(@RequestParam("filename") String fileName) throws IOException {
        File f = new File(FileService.CONTENT_DIR + fileName);

        if (!f.exists()){
            throw new FileNotFoundException();
        } //Cry out!

        return IOUtils.toByteArray(f.toURI());
    }
}
