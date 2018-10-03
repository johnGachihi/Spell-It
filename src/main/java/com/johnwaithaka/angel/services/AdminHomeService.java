package com.johnwaithaka.angel.services;

import com.johnwaithaka.angel.repositories.AngelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminHomeService {

    private AngelRepository angelRepository;

    @Autowired
    public AdminHomeService(AngelRepository angelRepository) {
        this.angelRepository = angelRepository;
    }

    public long getNumberOfRegisteredUsers(){
        return angelRepository.count();
    }
}
