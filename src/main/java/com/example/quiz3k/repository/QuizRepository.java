package com.example.quiz3k.repository;

import com.example.quiz3k.model.dao.QuizEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepository extends JpaRepository<QuizEntity, Long> {
}
