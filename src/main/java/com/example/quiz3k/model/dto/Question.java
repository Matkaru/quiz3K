package com.example.quiz3k.model.dto;

import com.example.quiz3k.enums.QuestionType;
import com.example.quiz3k.model.dto.Answer;
import jakarta.persistence.Enumerated;

import java.util.List;

public class Question {
    private long id;
    private String questionText;
    private List<Answer> answers;
    @Enumerated
    private QuestionType questionType;
}
