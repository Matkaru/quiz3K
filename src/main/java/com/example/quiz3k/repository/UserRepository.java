package com.example.quiz3k.repository;

import com.example.quiz3k.model.dao.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    //warunek - email oraz login - mają tą samą wartość i mają być przekazane parametrwoi
    @Query("select user from UserEntity user " +
            "where user.email = ?1 or user.login = ?1")
    Optional<UserEntity> findByEmail(String email);

    @Query
    Optional<UserEntity> findByEmailOrLogin(String email, String login);
}
