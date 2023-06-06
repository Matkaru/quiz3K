package com.example.quiz3k.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Quiz {

    private Long id;

    private String quizName;

    private List<Question> questions;

    private List<Answer> answers;

    public Quiz(Long id, String quizName) {
        this.id = id;
        this.quizName = quizName;
    }
}
