package com.example.quiz3k.model.dto;

import lombok.Data;

@Data
public class CreateAnswerRequest {
    private Long answerQuestionId;
    private String answerForTheQuestion;
    private boolean confirmedAnswer;

}
