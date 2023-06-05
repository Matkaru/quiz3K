package com.example.quiz3k.config;


import com.example.quiz3k.model.dao.UserEntity;
import com.example.quiz3k.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity entity = userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException(username));
        UserDetails user = User.builder()
                .username(username)
                .password(entity.getPassword())
                .roles(entity.getAuthorities().stream()
                        .map(x -> x.getLogin())
                        .toArray(String[]::new))
                .build();
        return user;
    }
}
