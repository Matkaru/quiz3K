package com.example.quiz3k.model.dto;

import com.example.quiz3k.enums.QuestionType;
import lombok.Data;

@Data
public class CreateQuestionRequest {
    private Long questionQuizId;
    private String questionText;
    private QuestionType questionType;
}
