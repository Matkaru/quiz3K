package com.example.quiz3k.repository;

import com.example.quiz3k.model.dao.QuestionEntity;
import com.example.quiz3k.model.dao.QuizEntity;
import com.example.quiz3k.model.dto.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<QuestionEntity, Long> {
    List<QuestionEntity> findByQuiz(QuizEntity quiz);
}
