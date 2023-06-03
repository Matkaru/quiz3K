package com.example.quiz3k.repository;

import com.example.quiz3k.model.dao.ActivationUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface UserActivationRepository extends JpaRepository<ActivationUserEntity, Long> {
    Optional<ActivationUserEntity> findByActivationToken(String token);

}
