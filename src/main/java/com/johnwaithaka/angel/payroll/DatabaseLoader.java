package com.johnwaithaka.angel.payroll;

import com.johnwaithaka.angel.entities.Admin;
import com.johnwaithaka.angel.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.xml.crypto.Data;

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
        this.adminRepository.save(new Admin("john@gmail.com", passwordEncoder.encode("1234")));

    }
}
