package com.johnwaithaka.angel.payroll;

import com.johnwaithaka.angel.Utility.BinarySearch;
import com.johnwaithaka.angel.entities.Admin;
import com.johnwaithaka.angel.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.xml.crypto.Data;
import java.util.List;

@Component
public class DatabaseLoader implements CommandLineRunner {

    private final EmployeeRepository employeeRepository;
    private final AdminRepository adminRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public DatabaseLoader(EmployeeRepository employeeRepository,
                          AdminRepository adminRepository,
                          PasswordEncoder passwordEncoder){
        this.employeeRepository = employeeRepository;
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        adminRepository.deleteAll();

        this.employeeRepository.save(new Employee("John", "Gachihi", "I do not know him"));
        for(int i = 0; i < 1; i++){
            this.adminRepository.save(new Admin("john@gmail.com", passwordEncoder.encode("1234")));
        }


       /* List<Admin> admins = this.adminRepository.findAll();
        for(Admin admin : admins){
            System.out.println(admin.getId());
        }*/

//        Admin a = BinarySearch.getLevelById(admins, "5ba78b4fafe7d43ac84ad9d3");
//        System.out.println(a != null ? a.toString() : "Null result");
    }
}
