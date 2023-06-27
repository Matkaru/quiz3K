package com.example.quiz3k.repository;

import com.example.quiz3k.model.dao.UserDataQuizEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDataQuizRepository extends CrudRepository<UserDataQuizEntity, Long> {

}
