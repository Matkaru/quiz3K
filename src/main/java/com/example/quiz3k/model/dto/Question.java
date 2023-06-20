package com.example.quiz3k.model.dto;

import com.example.quiz3k.enums.QuestionType;
import com.example.quiz3k.model.dto.Answer;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Question {
    private Long id;
    private String questionText;
    private List<Answer> answers;
    @Enumerated
    private QuestionType questionType;

    public String getQuestionText() {
        return questionText;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public QuestionType getQuestionType() {
        return questionType;
    }
}
