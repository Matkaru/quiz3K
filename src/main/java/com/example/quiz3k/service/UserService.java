package com.example.quiz3k.service;

import com.example.quiz3k.AppApiException;
import com.example.quiz3k.model.AppErrorMessage;
import com.example.quiz3k.model.UserResponse;
import com.example.quiz3k.model.dao.ActivationUserEntity;
import com.example.quiz3k.model.dao.UserEntity;
import com.example.quiz3k.model.dto.User;
import com.example.quiz3k.repository.AuthorityRepository;
import com.example.quiz3k.repository.UserActivationRepository;
import com.example.quiz3k.repository.UserRepository;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service

public class UserService {

    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;

    private final UserActivationRepository userActivationRepository;

    private final PasswordEncoder passwordEncoder;

@Autowired
    public UserService(UserRepository userRepository, AuthorityRepository authorityRepository, UserActivationRepository userActivationRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
        this.userActivationRepository = userActivationRepository;
        this.passwordEncoder = passwordEncoder;
}

    @Transactional
    public void createUser(User user) {
        userRepository.findByEmail(user.getEmail()).ifPresent((x) -> {
            throw new AppApiException(AppErrorMessage.USER_EXIST);
        });

        UserEntity savedUser = userRepository.save(UserEntity.builder()
                .email(user.getEmail())
                .password(passwordEncoder.encode(user.getPassword()))
                .login(user.getEmail())
                .build());

        ActivationUserEntity newToken = userActivationRepository.save(ActivationUserEntity.builder()
                .activationToken(UUID.randomUUID().toString())
                .user(savedUser)
                .build());
    }

    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public UserResponse getUser(String userName) {
        UserEntity userEntity = userRepository.findByEmail(userName)
                .orElseThrow(() -> new AppApiException(AppErrorMessage.USER_NOT_FOUND));

        return toDto(userEntity);
    }

    private UserResponse toDto(UserEntity entity) {
        return UserResponse.builder()
                .email(entity.getEmail())
                .login(entity.getLogin())
                .build();
    }

    @Transactional
    public void activateUser(String token) {
        ActivationUserEntity activationUserEntity = userActivationRepository.findByActivationToken(token)
                .orElseThrow(() -> new AppApiException(AppErrorMessage.INVALID_ACTIVATION_TOKEN));

        activationUserEntity.getUser().setActive(true);
        userActivationRepository.delete(activationUserEntity);
    }
}
