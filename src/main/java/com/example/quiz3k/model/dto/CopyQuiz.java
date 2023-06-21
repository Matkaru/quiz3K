package com.example.quiz3k.model.dto;

import com.example.quiz3k.enums.QuestionType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class CopyQuiz {

    private Long id;

    private String copyQuizName;

    private Long questionQuizId;

    private String copyQuestionText;

    @Enumerated
    private QuestionType questionType;

    private Long answerQuestionId;

    private Long answerId;

    private String copyAnswerForTheQuestion;

    private Long userAnswer;

    public CopyQuiz() {
    }

    public CopyQuiz(Long questionQuizId, String quizName, Long answerQuestionId, String questionText,
                    QuestionType questionType, Long answerId, String answerForTheQuestion) {
    }
}
