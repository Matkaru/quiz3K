package com.example.quiz3k.model.dao;

import com.example.quiz3k.enums.UserType;
import jakarta.persistence.*;
import lombok.Data;

@Table(name = "users")
@Entity
@Data
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String login;

    private String email;

    private String password;

    @Enumerated
    private UserType userType;
}
