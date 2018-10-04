package com.johnwaithaka.angel.services;

import com.johnwaithaka.angel.DTOs.AngelDTO;
import com.johnwaithaka.angel.entities.Angel;
import com.johnwaithaka.angel.repositories.AngelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AdminHomeService {

    /*This class should not exist
    * Transfer everything to AngelService*/

    private AngelRepository angelRepository;

    @Autowired
    public AdminHomeService(AngelRepository angelRepository) {
        this.angelRepository = angelRepository;
    }

    public long getNumberOfRegisteredUsers(){
        return angelRepository.count();
    }

}
