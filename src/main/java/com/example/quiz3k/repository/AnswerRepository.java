package com.example.quiz3k.repository;

import com.example.quiz3k.model.dao.AnswerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<AnswerEntity, Long> {
}
