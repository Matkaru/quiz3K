package com.example.quiz3k.service;

public class QuizNotFoundException extends RuntimeException{
    public QuizNotFoundException(String message) {
        super(message);
    }
}
