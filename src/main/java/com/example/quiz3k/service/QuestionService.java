package com.example.quiz3k.service;

import com.example.quiz3k.enums.QuestionType;
import com.example.quiz3k.model.dao.QuestionEntity;
import com.example.quiz3k.model.dao.QuizEntity;
import com.example.quiz3k.model.dto.Question;
import com.example.quiz3k.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final QuestionEntity entity;

    @Autowired
    public QuestionService(QuestionRepository questionRepository, QuestionEntity questionEntity) {
        this.questionRepository = questionRepository;
        this.entity = questionEntity;
    }

    public QuestionEntity createQuestion(String questionText, QuestionType questionType){
        entity.setQuiz(new QuizEntity());
        entity.setQuestionText(questionText);
        entity.setQuestionType(questionType);
        return questionRepository.save(entity);
    }

    public List<QuestionEntity> getQuestionsByQuiz(QuizEntity quiz){
        return questionRepository.findByQuiz(quiz);
    }
}
