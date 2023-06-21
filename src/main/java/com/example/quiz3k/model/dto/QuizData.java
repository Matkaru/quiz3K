package com.example.quiz3k.model.dto;

import com.example.quiz3k.enums.QuestionType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class QuizData {
    private String email;

    private Long id;

    private String quizName;

    private Long quizId;

    private String copyQuestionText;

    @Enumerated
    private QuestionType questionType;

    private Long answerQuestionId;

    private Long answerId;

    private String copyAnswerForTheQuestion;

    private Long userAnswer;

    public QuizData() {
    }

    public QuizData(Long questionQuizId, String quizName, Long answerQuestionId, String questionText,
                    QuestionType questionType, Long answerId, String answerForTheQuestion) {
    }
}
