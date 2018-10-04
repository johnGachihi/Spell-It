package com.johnwaithaka.angel.services;

import com.johnwaithaka.angel.DTOs.AngelDTO;
import com.johnwaithaka.angel.config.UserRoles;
import com.johnwaithaka.angel.entities.Angel;
import com.johnwaithaka.angel.exception.UsernameExistsException;
import com.johnwaithaka.angel.repositories.AngelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AngelService {

    private AngelRepository angelRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AngelService(AngelRepository angelRepository, PasswordEncoder passwordEncoder) {
        this.angelRepository = angelRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Integer> getRegistrationDateCountData(){
        List<Integer> registrationData = new ArrayList<>();
        for(int i = 1; i <=12; i++){
            long count = angelRepository.countByRegDate(i, new Date());
            registrationData.add((int)count);
        }
        return registrationData;
    }

    public Angel registerUser(AngelDTO angelDTO) throws UsernameExistsException {
        if(usernameExists(angelDTO))
            throw new UsernameExistsException("Username exists");
        Angel angel = new Angel();
        angel.setUsername(angelDTO.getUsername());
        angel.setPassword(passwordEncoder.encode(angelDTO.getPassword()));
        angel.setRegDate(new Date());
        angel.addRole(UserRoles.USER);
        angelRepository.save(angel);
        return angel;
    }

    private boolean usernameExists(AngelDTO angelDTO){
        Optional<Angel> o = angelRepository.findByUsername(angelDTO.getUsername());
        return o.isPresent();
    }
}
