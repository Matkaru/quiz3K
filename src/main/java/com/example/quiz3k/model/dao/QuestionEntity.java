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

    private Long questionQuizId;

    @ManyToOne(cascade = {CascadeType.ALL})
    private QuizEntity quiz;

    @Enumerated
    private QuestionType questionType;
}
