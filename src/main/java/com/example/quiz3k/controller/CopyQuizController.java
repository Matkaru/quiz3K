package com.example.quiz3k.controller;

import com.example.quiz3k.model.dto.QuizData;
import com.example.quiz3k.repository.CopyQuizRepository;
import com.example.quiz3k.service.CopyQuizService;
import com.example.quiz3k.service.QuestionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
public class CopyQuizController {

    private final CopyQuizService copyService;

    @Autowired
    public CopyQuizController(CopyQuizService copyService) {
        this.copyService = copyService;
    }

    @PostMapping(path = "/api/share/quiz/{id}")
    public ResponseEntity<String> saveQuiz(@RequestBody QuizData quizData) {
        copyService.saveQuiz(quizData);
        return ResponseEntity.ok("Quiz został zapisany pomyślnie");
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public void handleException(Exception e){
        log.error(e.getMessage());
    }

}
