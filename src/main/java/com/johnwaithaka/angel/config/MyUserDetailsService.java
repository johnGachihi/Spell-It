package com.johnwaithaka.angel.config;

import com.johnwaithaka.angel.entities.Admin;
import com.johnwaithaka.angel.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private AdminRepository adminRepository;

    @Autowired
    public MyUserDetailsService(AdminRepository adminRepository){
        this.adminRepository = adminRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = adminRepository.findByUsername(username);
        if(admin == null){
            throw new UsernameNotFoundException(username);
        }
        return User.withUsername(admin.getUsername())
                .password(admin.getPassword())
                .roles("ADMIN")
                .disabled(false)
                .build();
    }
}
