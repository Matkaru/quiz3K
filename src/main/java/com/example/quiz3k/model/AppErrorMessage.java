package com.example.quiz3k.model;

import lombok.Getter;

@Getter
public enum AppErrorMessage {
    MISSING_USER("User with username '%s' not found", 512),
    USER_EXIST("User with provided email or nick currently exist", 404), INVALID_ACTIVATION_TOKEN;

    public static final String USER_NOT_FOUND = "Nieprawidłowy token aktywacyjny.";
    private String messageTemplate;
    private int status;

    AppErrorMessage(String messageTemplate, int status) {
        this.messageTemplate = messageTemplate;
        this.status = status;
    }


    AppErrorMessage() {

    }
}
