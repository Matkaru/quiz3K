package com.example.quiz3k.model.dto;

import com.example.quiz3k.enums.QuestionType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.util.List;

@Data
public class QuizData {

    private Long quizId;
    private String email;
    private List<SharedAnswer> userAnswerIdList;

    public QuizData() {
    }

}

