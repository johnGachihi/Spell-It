package com.johnwaithaka.angel.DTOs;

import com.johnwaithaka.angel.validation.ValidEmail;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class AdminDto {
    @ValidEmail
    @NotNull
    @NotEmpty
    private String email;
    private String password;

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
