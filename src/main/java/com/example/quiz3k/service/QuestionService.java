package com.example.quiz3k.service;

import com.example.quiz3k.enums.QuestionType;
import com.example.quiz3k.model.dao.QuestionEntity;
import com.example.quiz3k.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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


    public List<QuestionEntity> getQuestionsByQuizId(Long questionQuizId) {
        return questionRepository.findByQuestionQuizId(questionQuizId);
    }
//    public List<String> getQuestionsByQuizId(Long questionQuizId) {
//        List<String> questionTexts = new ArrayList<>();
//        List<QuestionEntity> questions = questionRepository.findByQuestionQuizId(questionQuizId);
//
//        questionTexts = questions.stream()
//                .map(QuestionEntity::getQuestionText)
//                .collect(Collectors.toList());
//
//        return questionTexts;
//    }

    public Optional<QuestionEntity> getQuestionById(Long id){
        return questionRepository.findById(id);
    }

    public void deleteQuestion(Long id){
        questionRepository.deleteById(id);
    }

    public QuestionEntity updateQuestion(QuestionEntity question) {
        return questionRepository.save(question);
    }
}
