package com.example.quiz3k.model.dto;

import com.example.quiz3k.enums.UserType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotEmpty;

public class User {

    private Long id;

    @NotEmpty(message = "musi zawierac login!")
    private String login;

    @NotEmpty(message = "musi zawierac email!")
    private String email;

    @NotEmpty(message = "musi zawierac hasło!")
    private String password;

    @Enumerated
    private UserType userType;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}

