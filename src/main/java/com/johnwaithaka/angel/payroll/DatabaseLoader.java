package com.johnwaithaka.angel.payroll;

import com.johnwaithaka.angel.Utility.BinarySearch;
import com.johnwaithaka.angel.entities.*;
import com.johnwaithaka.angel.repositories.AdminRepository;
import com.johnwaithaka.angel.repositories.AngelRepository;
import com.johnwaithaka.angel.repositories.LevelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.xml.crypto.Data;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Component
public class DatabaseLoader implements CommandLineRunner {

    private final EmployeeRepository employeeRepository;
    private final AdminRepository adminRepository;
    private PasswordEncoder passwordEncoder;
    private final LevelRepository levelRepository;
    private final AngelRepository angelRepository;

    @Autowired
    public DatabaseLoader(EmployeeRepository employeeRepository,
                          AdminRepository adminRepository,
                          PasswordEncoder passwordEncoder,
                          LevelRepository levelRepository,
                          AngelRepository angelRepository){
        this.employeeRepository = employeeRepository;
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
        this.levelRepository = levelRepository;
        this.angelRepository = angelRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        adminRepository.deleteAll();
//        levelRepository.deleteAll();

        this.employeeRepository.save(new Employee("John", "Gachihi", "I do not know him"));
        for(int i = 0; i < 1; i++){
            Admin admin = new Admin("john@gmail.com", passwordEncoder.encode("1234"));
            admin.addRole("ADMIN");
            admin.addRole("USER");
            this.adminRepository.save(admin);
        }

        Level l = new Level();
        l.addLesson(
            new Lesson(
                new Word(
                        "ble",
                        Arrays.asList(new String[]{"b", "le"}),
                        new File("C:/users/john/Pictures/good images/cup_and_blue.jpg").getName(),
                        new File("C:/users/john/Pictures/good images/cup_and_blue.jpg").getName()
                )
        ));

        if(levelRepository.count() < 2){
            levelRepository.save(l);
        }

        /*for(int i = 0 ; i < 11; i++){
            Angel user = new Angel();
            user.setUsername("john" + i);
            user.setRegDate(new Date());

            angelRepository.save(user);
        }*/

       /* List<Admin> admins = this.adminRepository.findAll();
        for(Admin admin : admins){
            System.out.println(admin.getId());
        }*/

//        Admin a = BinarySearch.getLevelById(admins, "5ba78b4fafe7d43ac84ad9d3");
//        System.out.println(a != null ? a.toString() : "Null result");
    }
}
