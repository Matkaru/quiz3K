package com.example.quiz3k.model.dao;

import com.example.quiz3k.enums.QuestionType;
import jakarta.persistence.*;
import lombok.Data;

@Table(name = "copy_quiz")
@Entity
@Data
public class CopyQuizEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String quizName;

    private Long questionQuizId;
    private Long quizId;

    private String questionText;

    @Enumerated
    private QuestionType questionType;

    private Long answerQuestionId;

    private Long answerId;

    private String AnswerForTheQuestion;

    private Long userAnswer;
}
