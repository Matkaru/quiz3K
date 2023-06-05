package com.example.quiz3k.config;


import com.example.quiz3k.model.dao.AuthorityEntity;
import com.example.quiz3k.repository.AuthorityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Order(1)
public class AuthorityInitializer implements CommandLineRunner {

    private final AuthorityRepository authorityRepository;

    @Override
    public void run(String... args) throws Exception {
        createAndSaveAuthority("ADMIN");
        createAndSaveAuthority("USER");
    }

    private void createAndSaveAuthority(String authorityName){
        AuthorityEntity authorityEntity = new AuthorityEntity();

        authorityEntity.setLogin(authorityName);

        authorityRepository.save(authorityEntity);
    }
}
