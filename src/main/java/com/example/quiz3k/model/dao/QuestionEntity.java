package com.example.quiz3k.model.dao;

import com.example.quiz3k.enums.QuestionType;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Table(name = "question")
@Entity
@Data
public class QuestionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String questionText;

    @OneToMany(
            mappedBy = "question",
            cascade = {CascadeType.ALL})
    private List<AnswerEntity> answers;

    @ManyToOne(cascade = {CascadeType.ALL})
    private QuizEntity quizId = getQuizId();

    @Enumerated
    private QuestionType questionType;
}
