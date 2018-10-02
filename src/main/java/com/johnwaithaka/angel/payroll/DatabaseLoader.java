package com.johnwaithaka.angel.payroll;

import com.johnwaithaka.angel.Utility.BinarySearch;
import com.johnwaithaka.angel.entities.Admin;
import com.johnwaithaka.angel.entities.Lesson;
import com.johnwaithaka.angel.entities.Level;
import com.johnwaithaka.angel.entities.Word;
import com.johnwaithaka.angel.repositories.AdminRepository;
import com.johnwaithaka.angel.repositories.LevelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.xml.crypto.Data;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class DatabaseLoader implements CommandLineRunner {

    private final EmployeeRepository employeeRepository;
    private final AdminRepository adminRepository;
    private PasswordEncoder passwordEncoder;
    private final LevelRepository levelRepository;

    @Autowired
    public DatabaseLoader(EmployeeRepository employeeRepository,
                          AdminRepository adminRepository,
                          PasswordEncoder passwordEncoder,
                          LevelRepository levelRepository){
        this.employeeRepository = employeeRepository;
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
        this.levelRepository = levelRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        adminRepository.deleteAll();
//        levelRepository.deleteAll();

        this.employeeRepository.save(new Employee("John", "Gachihi", "I do not know him"));
        for(int i = 0; i < 1; i++){
            this.adminRepository.save(new Admin("john@gmail.com", passwordEncoder.encode("1234")));
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

        levelRepository.save(l);

       /* List<Admin> admins = this.adminRepository.findAll();
        for(Admin admin : admins){
            System.out.println(admin.getId());
        }*/

//        Admin a = BinarySearch.getLevelById(admins, "5ba78b4fafe7d43ac84ad9d3");
//        System.out.println(a != null ? a.toString() : "Null result");
    }
}
