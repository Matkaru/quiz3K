package com.example.quiz3k.service;

import com.example.quiz3k.model.dao.CopyQuizEntity;
import com.example.quiz3k.model.dto.QuizData;
import com.example.quiz3k.repository.CopyQuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CopyQuizService {

    private final CopyQuizRepository copyRepo;

    @Autowired
    public CopyQuizService(CopyQuizRepository copyRepo) {
        this.copyRepo = copyRepo;
    }

    public void saveQuiz(QuizData quizData) {
        CopyQuizEntity copyQuiz = new CopyQuizEntity();
        copyQuiz.setQuizName(quizData.getQuizName());
        copyQuiz.setQuizId(quizData.getQuizId());
        copyRepo.save(copyQuiz);
    }
}