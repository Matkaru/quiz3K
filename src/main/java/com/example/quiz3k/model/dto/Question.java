package com.example.quiz3k.model.dto;

import com.example.quiz3k.enums.QuestionType;
import com.example.quiz3k.model.dto.Answer;
import jakarta.persistence.Enumerated;

import java.util.List;

public class Question {
    private Long id;
    private String questionName;
    private String questionText;
    private List<Answer> answers;
    @Enumerated
    private QuestionType questionType;

    public Question() {
    }

    public Question(String questionName, String questionText, List<Answer> answers, QuestionType questionType) {
        this.questionName = questionName;
        this.questionText = questionText;
        this.answers = answers;
        this.questionType = questionType;
    }

    public String getQuestionName() {
        return questionName;
    }

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
