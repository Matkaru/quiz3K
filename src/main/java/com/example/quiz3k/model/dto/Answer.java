package com.example.quiz3k.model.dto;
import jakarta.validation.constraints.NotEmpty;

public class Answer {
    private Long id;
    @NotEmpty(message = "musi zawierac tekst")
    private String answerForTheQuestion;
    private boolean confirmedAnswer;

}