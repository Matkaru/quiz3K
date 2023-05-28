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

    @OneToOne
    private UserEntity userEntity;

    @OneToMany(mappedBy = "quiz")
    private List<QuestionEntity> questions;

}
