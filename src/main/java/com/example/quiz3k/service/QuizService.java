package com.example.quiz3k.service;

import com.example.quiz3k.model.dao.QuizEntity;
import com.example.quiz3k.model.dto.Quiz;
import com.example.quiz3k.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuizService {

    private final QuizRepository quizRepository;

    @Autowired
    public QuizService(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }


    public QuizEntity createQuiz(String quizName) {

        QuizEntity quizEntity = new QuizEntity();
        quizEntity.setQuizName(quizName);
        return quizRepository.save(quizEntity);
    }
    public List<Quiz> getAllQuiz() {
        List<QuizEntity> quizEntities = quizRepository.findAll();
        return quizEntities.stream()
                .map(entity -> new Quiz(entity.getId(), entity.getQuizName()))
                .collect(Collectors.toList());
    }
//    public List<String> getAllQuiz() {
//        return quizRepository.findAll().stream().map(x -> x.getQuizName()).collect(Collectors.toList());
//    }

    public void deleteQuiz(Long id) {
        quizRepository.deleteById(id);
    }
}
