package com.example.quiz3k.model;
import jakarta.validation.constraints.NotEmpty;

public class Answer {
    private long id;
    @NotEmpty
    private String answerForTheQuestion;
    private boolean confirmedAnswer;

}
