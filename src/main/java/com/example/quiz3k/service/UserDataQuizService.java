package com.example.quiz3k.service;
import com.example.quiz3k.model.dao.UserDataQuizEntity;
import com.example.quiz3k.model.dto.QuizData;
import com.example.quiz3k.repository.UserDataQuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;


@Service
public class UserDataQuizService {

    private final UserDataQuizRepository copyRepo;

    @Autowired
    public UserDataQuizService(UserDataQuizRepository copyRepo) {
        this.copyRepo = copyRepo;
    }

    public void saveQuiz(QuizData quizData) {
        UserDataQuizEntity copyQuiz = new UserDataQuizEntity();
        copyQuiz.setQuizId(quizData.getQuizId());
        copyQuiz.setUserAnswerIdList(quizData.getUserAnswerIdList().stream()
                .flatMap(x -> x.getAnswers().stream())
                .collect(Collectors.toList()));
        copyRepo.save(copyQuiz);
    }
}
