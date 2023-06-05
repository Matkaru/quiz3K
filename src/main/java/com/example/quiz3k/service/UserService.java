package com.example.quiz3k.service;

import com.example.quiz3k.UserCopyApiException;
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
  //  private final EmailService emailService;

   //@Value("${spring.email.username}")
   //private String senderEmail;
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
            throw new UserCopyApiException(AppErrorMessage.USER_EXIST);

        });
        Function<User, UserEntity> convertDtoToDao = (User newUser) -> UserEntity.builder()
                .email(user.getEmail())
                .password(passwordEncoder.encode(user.getPassword()))
                .login(user.getEmail())
                .build();


        UserEntity savedUser = userRepository.save(convertDtoToDao.apply(user));
        ActivationUserEntity newToken = userActivationRepository.save(ActivationUserEntity.builder()
                .activationToken(UUID.randomUUID().toString())
                .user(savedUser)
                .build()
        );
 //      //nie stworzone ze względu na brak fremwork - musi zwrócić komunikat
 //      SimpleMailMessage emailMessage = new SimpleMailMessage();
 //   emailMessage.setTo(savedUser.getEmail());
 //   emailMessage.setSubject("Complete Registration!");
 //   emailMessage.setFrom(senderEmail);
 //   emailMessage.setText("To confirm your account, please click here : " // aby potwierdzić konto kliknij "tutaj"
 //            +"http://localhost:8080/api/activate-user/" + newToken.getActivationToken());

 //   emailService.sendEmail(emailMessage);
    }

    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    public UserResponse getUser(String userName) {
        return userRepository.findByEmail(userName).map(this::toDto).orElseThrow();
    }

    private UserResponse toDto(UserEntity entity) {
        return UserResponse.builder().email(entity.getEmail()).login(entity.getLogin()).build();
    }

    @Transactional
    public void activateUser(String token) {
        ActivationUserEntity activationUserEntity = userActivationRepository.findByActivationToken(token).orElseThrow();
        activationUserEntity.getUser().setActive(true);
        userActivationRepository.delete(activationUserEntity);
    }


}
