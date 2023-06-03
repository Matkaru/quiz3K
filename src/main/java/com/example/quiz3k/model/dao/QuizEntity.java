package com.example.quiz3k.model.dao;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Table(name = "quiz")
@Entity
@Data
public class QuizEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String quizName;

    @OneToOne(cascade = {CascadeType.ALL})
    private UserEntity userEntity;

    @OneToMany(
            mappedBy = "quizId",
            cascade = {CascadeType.ALL})
    private List<QuestionEntity> questions;

}
