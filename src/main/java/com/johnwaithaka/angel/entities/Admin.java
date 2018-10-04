package com.johnwaithaka.angel.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Data
public class Admin {

    @Id @GeneratedValue
    private String id;
    private String username;
    private String password;
    private boolean enabled;
    private List<String> roles;

    public Admin() {}

    public Admin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void addRole(String role){
        if (roles == null)
            roles = new ArrayList<>();

        roles.add(role);
    }
}
