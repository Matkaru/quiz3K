package com.example.quiz3k.repository;

import com.example.quiz3k.model.dao.AnswerEntity;
import com.example.quiz3k.model.dao.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AnswerRepository extends JpaRepository<AnswerEntity, Long> {
    Optional<AnswerEntity> findByQuestionAndConfirmedAnswer(QuestionEntity question, boolean b);

    List<AnswerEntity> findByAnswerQuestionId(Long answerQuestionId);
}
