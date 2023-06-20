package com.example.quiz3k.service;

import com.example.quiz3k.enums.QuestionType;
import com.example.quiz3k.model.dao.AnswerEntity;
import com.example.quiz3k.model.dao.CopyQuizEntity;
import com.example.quiz3k.model.dao.QuestionEntity;
import com.example.quiz3k.model.dao.QuizEntity;
import com.example.quiz3k.model.dto.CopyQuiz;
import com.example.quiz3k.repository.AnswerRepository;
import com.example.quiz3k.repository.CopyQuizRepository;
import com.example.quiz3k.repository.QuestionRepository;
import com.example.quiz3k.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CopyQuizService {

    private final CopyQuizRepository copyRepo;
    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;
    private final QuizRepository quizRepository;

    @Autowired
    public CopyQuizService(CopyQuizRepository copyRepo, AnswerRepository answerRepository, QuestionRepository questionRepository, QuizRepository quizRepository) {
        this.copyRepo = copyRepo;
        this.answerRepository = answerRepository;
        this.questionRepository = questionRepository;
        this.quizRepository = quizRepository;
    }

    public List<CopyQuiz> createQuizCopy
            (String quizName, Long questionQuizId, String questionText,
             QuestionType questionType, Long answerQuestionId, Long answerId,
             String answerForTheQuestion){

        QuizEntity quizEntity = new QuizEntity();
        QuestionEntity questionEntity = new QuestionEntity();
        AnswerEntity answerEntity = new AnswerEntity();

        return Stream.of(new Object()) //
                .map(object -> new CopyQuiz(
                        questionEntity.getQuestionQuizId(),
                        quizEntity.getQuizName(),
                        answerEntity.getAnswerQuestionId(),
                        questionEntity.getQuestionText(),
                        questionEntity.getQuestionType(),
                        answerEntity.getId(),
                        answerEntity.getAnswerForTheQuestion()
                ))
                .collect(Collectors.toList());
    }

}
