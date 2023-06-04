package com.example.quiz3k.repository;

import com.example.quiz3k.model.dao.AuthorityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorityRepository extends JpaRepository<AuthorityEntity, Long> {
    Optional<AuthorityEntity> findByLogin(String login);
}
