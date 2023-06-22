package com.example.quiz3k.model.dto;

import com.example.quiz3k.enums.QuestionType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.util.List;

@Data
public class QuizData {
    private String email;

    private Long id;

    private Long quizId;

    private Long answerQuestionId;

    private List<Long> userAnswerIdList;

    public QuizData() {
    }

}
