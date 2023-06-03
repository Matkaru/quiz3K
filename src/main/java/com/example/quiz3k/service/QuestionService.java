package com.example.quiz3k.service;

import com.example.quiz3k.enums.QuestionType;
import com.example.quiz3k.model.dao.QuestionEntity;
import com.example.quiz3k.model.dao.QuizEntity;
import com.example.quiz3k.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    @Autowired
    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public QuestionEntity createQuestion(String questionText, QuestionType questionType){
        QuestionEntity entity = new QuestionEntity();

        entity.setQuestionText(questionText);
        entity.setQuestionType(questionType);
        return questionRepository.save(entity);
    }

//    public List<QuestionEntity> getQuestionsByQuiz(QuizEntity quizId){
//        return new ArrayList<>(questionRepository.findByQuiz(quizId));
//    }

    public List<String> getAllQuestions(){
        return questionRepository.findAll().stream()
                .map(QuestionEntity::getQuestionText)
                .collect(Collectors.toList());
    }

    public Optional<QuestionEntity> getQuestionById(Long id){
        return questionRepository.findById(id);
    }

    public void deleteQuestion(Long id){
        questionRepository.deleteById(id);
    }
}
