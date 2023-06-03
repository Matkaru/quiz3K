package com.example.quiz3k.model.dao;

import jakarta.persistence.*;
import lombok.Data;

@Table(name = "answer")
@Entity
@Data
public class AnswerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String answerForTheQuestion;

    private boolean confirmedAnswer;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "question")
    private QuestionEntity question;
}
