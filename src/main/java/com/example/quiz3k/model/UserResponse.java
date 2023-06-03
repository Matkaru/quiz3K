package com.example.quiz3k.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserResponse {
    private String email;
    private String login;
}
