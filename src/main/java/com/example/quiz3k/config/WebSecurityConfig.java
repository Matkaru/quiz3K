package com.example.quiz3k.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{

        httpSecurity
                .csrf(AbstractHttpConfigurer::disable);
        httpSecurity.httpBasic(Customizer.withDefaults());
        httpSecurity.cors(Customizer.withDefaults());
        httpSecurity.headers(httpSecurityHeadersConfigurer -> httpSecurityHeadersConfigurer.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin));
        httpSecurity.authorizeHttpRequests(
                (request -> request
                        .requestMatchers(HttpMethod.GET, "/api/users").authenticated()
                        .requestMatchers(HttpMethod.POST, "/api/quiz").authenticated()
                        .requestMatchers(HttpMethod.GET, "/api/quiz").authenticated()
                        .requestMatchers(HttpMethod.PUT, "/api/quiz/{id}").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/api/quiz/{id}").authenticated()
                        .requestMatchers(HttpMethod.GET, "/api/users").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/users/info").authenticated()
                        .requestMatchers(HttpMethod.GET, "api/users-user").authenticated()
                        .requestMatchers(HttpMethod.POST, "/api/answers").authenticated()
                        .requestMatchers(HttpMethod.PUT, "/api/questions/{id}").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/api/answers/{id}").authenticated()
                        .requestMatchers(HttpMethod.PUT, "/api/answers/{id}").authenticated()
                        .requestMatchers(HttpMethod.POST, "/api/questions").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/api/questions/{id}").authenticated()
                        .requestMatchers(HttpMethod.PUT, "/api/questions/{id}").authenticated()
                        .anyRequest().authenticated()
                )
        );

        return httpSecurity.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
                .requestMatchers(HttpMethod.POST, "/rejestracja")
                .requestMatchers(HttpMethod.POST, "/api/users")
                .requestMatchers(HttpMethod.GET, "/api/users")
                .requestMatchers(HttpMethod.GET, "/api/quiz/{id}")
                .requestMatchers(HttpMethod.GET, "/api/questions")
                .requestMatchers(HttpMethod.GET, "/api/answers")
                .requestMatchers(HttpMethod.POST, "/api/share/quiz/*");

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


}

