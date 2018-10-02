package com.johnwaithaka.angel.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class FileService {

    private static final String CONTENT_DIR = "content/";

    private File createParentFolder(){
        File p = new File(CONTENT_DIR);
        if(!(p.exists())) {
            p.mkdir();
        }
        return p;
    }

    public File multipartToFile(MultipartFile mf) {
        String suffix = mf.getOriginalFilename().substring(
                mf.getOriginalFilename().lastIndexOf("."));

        File parent = createParentFolder();

        File f = null;
        try {
            f = File.createTempFile("SIT", suffix, parent);
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
