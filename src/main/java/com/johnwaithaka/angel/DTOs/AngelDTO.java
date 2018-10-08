package com.johnwaithaka.angel.DTOs;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class AngelDTO {
    @NotEmpty
    @NotNull
    private String username;
    private String name;
    @NotEmpty
    @NotNull
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
