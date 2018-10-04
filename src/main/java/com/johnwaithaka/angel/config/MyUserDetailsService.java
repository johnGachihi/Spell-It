package com.johnwaithaka.angel.config;

import com.johnwaithaka.angel.entities.Admin;
import com.johnwaithaka.angel.entities.Angel;
import com.johnwaithaka.angel.repositories.AdminRepository;
import com.johnwaithaka.angel.repositories.AngelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private AdminRepository adminRepository;
    private AngelRepository angelRepository;

    @Autowired
    public MyUserDetailsService(AdminRepository adminRepository, AngelRepository angelRepository){
        this.adminRepository = adminRepository;
        this.angelRepository = angelRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        /*Admin admin = adminRepository.findByUsername(username);
        if(admin == null){
            throw new UsernameNotFoundException(username);
        }

        String[] roles = new String[admin.getRoles().size()];
        roles = admin.getRoles().toArray(roles);

        return User.withUsername(admin.getUsername())
                .password(admin.getPassword())
                .roles(roles)
                .disabled(false)
                .build();*/
        Admin admin = adminRepository.findByUsername(username);
        if(admin != null){
            String[] roles = new String[admin.getRoles().size()];
            roles = admin.getRoles().toArray(roles);

            return User.withUsername(admin.getUsername())
                    .password(admin.getPassword())
                    .roles(roles)
                    .disabled(false)
                    .build();
        }
        Optional<Angel> o = angelRepository.findByUsername(username);
        if(o.isPresent()){
            Angel angel = o.get();

            String[] roles = new String[angel.getRoles().size()];
            roles = angel.getRoles().toArray(roles);

            return User.withUsername(angel.getUsername())
                    .password(angel.getPassword())
                    .roles(roles)
                    .disabled(false)
                    .build();
        }

        throw new UsernameNotFoundException("Username not found");
    }
}
