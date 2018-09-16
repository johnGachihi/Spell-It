package com.johnwaithaka.angel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class AngelApplication {

    public static void main(String[] args) {
        SpringApplication.run(AngelApplication.class, args);
    }

}
