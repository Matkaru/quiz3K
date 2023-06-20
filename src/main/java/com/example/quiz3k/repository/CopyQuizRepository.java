package com.example.quiz3k.repository;

import com.example.quiz3k.model.dao.CopyQuizEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CopyQuizRepository extends JpaRepository<CopyQuizEntity, Long> {

}
