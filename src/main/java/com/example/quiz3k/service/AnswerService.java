package com.example.quiz3k.service;
import com.example.quiz3k.model.dao.AnswerEntity;
import com.example.quiz3k.model.dao.QuestionEntity;
import com.example.quiz3k.model.dto.Answer;
import com.example.quiz3k.repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AnswerService {

    private final AnswerRepository answerRepository;

    @Autowired
    public AnswerService(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    public AnswerEntity createAnswer(Answer answer) {
        AnswerEntity answerEntity = new AnswerEntity();
        answerEntity.setAnswerForTheQuestion(answer.getAnswerForTheQuestion());
        answerEntity.setConfirmedAnswer(answer.isConfirmedAnswer());
        answerEntity.setAnswerQuestionId(answer.getAnswerQuestionId());

        return  answerRepository.save(answerEntity);
    }

    public List<String> getAllAnswers() {
        return answerRepository.findAll().stream()
                .map(AnswerEntity::getAnswerForTheQuestion)
                .collect(Collectors.toList());
    }

    public Optional<AnswerEntity> getAnswerById(Long id) {
        return answerRepository.findById(id);
    }

    public AnswerEntity getCorrectAnswer(QuestionEntity question) {
        Optional<AnswerEntity> correctAnswer = answerRepository.findByQuestionAndConfirmedAnswer(question, true);
        return correctAnswer.orElse(null);
    }

    public void deleteAnswer(Long id) {
        answerRepository.deleteById(id);
    }

    public List<String> getAnswersByQuestionId(Long answerQuestionId) {
        List<String> answerForTheQuestion = new ArrayList<>();
        List<AnswerEntity> answers = answerRepository.findByAnswerQuestionId(answerQuestionId);

        answerForTheQuestion = answers.stream()
                .map(AnswerEntity::getAnswerForTheQuestion)
                .collect(Collectors.toList());
        return answerForTheQuestion;

    }

}
