package com.example.quiz3k.controller;

import com.example.quiz3k.model.dto.QuizData;
import com.example.quiz3k.service.UserDataQuizService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
public class UserDataQuizController {

    private final UserDataQuizService copyService;

    @Autowired
    public UserDataQuizController(UserDataQuizService copyService) {
        this.copyService = copyService;
    }

    @PostMapping(path = "/api/share/quiz/{id}")
    public ResponseEntity<QuizData> saveQuiz(@RequestBody QuizData quizData) {
        copyService.saveQuiz(quizData);
        return ResponseEntity.status(HttpStatus.CREATED).body(quizData);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public void handleException(Exception e){
        log.error(e.getMessage());
    }

}
