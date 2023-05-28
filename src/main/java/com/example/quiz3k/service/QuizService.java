package com.example.quiz3k.service;

import com.example.quiz3k.model.dao.QuizEntity;
import com.example.quiz3k.model.dto.Quiz;
import com.example.quiz3k.repository.AnswerRepository;
import com.example.quiz3k.repository.QuestionRepository;
import com.example.quiz3k.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuizService {

    private final QuizRepository quizRepository;
    @Autowired
    public QuizService(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }



    public QuizEntity createQuiz(String quizName){

        QuizEntity quizEntity = new QuizEntity();
        quizEntity.setQuizName(quizName);
        return quizRepository.save(quizEntity);
    }



}
