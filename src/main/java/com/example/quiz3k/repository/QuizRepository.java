package com.example.quiz3k.repository;

import com.example.quiz3k.model.dao.QuizEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QuizRepository extends JpaRepository<QuizEntity, Long> {
    Optional<QuizEntity> findById (Long Id);
}
