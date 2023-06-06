package com.example.quiz3k.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{

        httpSecurity.csrf(csrf -> csrf.disable());
//        httpSecurity.formLogin(form -> form.permitAll());
        httpSecurity.httpBasic(Customizer.withDefaults());
        httpSecurity.cors(Customizer.withDefaults());
        httpSecurity.authorizeHttpRequests(
                (request -> request
                        .requestMatchers(HttpMethod.POST, "/api/users").permitAll()
                        .requestMatchers(HttpMethod.POST, "/rejestracja").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/users").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/users-user").authenticated()
                        .requestMatchers(HttpMethod.POST, "/api/quiz").authenticated()
                        .requestMatchers(HttpMethod.GET, "/api/quiz").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/api/quiz/{id}").authenticated()
                        .requestMatchers(HttpMethod.GET, "/api/users").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/users/info").authenticated()
                        .requestMatchers(HttpMethod.POST, "/api/answers").authenticated()
                        .requestMatchers(HttpMethod.GET, "/api/answers").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/api/answers/{id}").authenticated()
                )
        );

        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}

