package com.example.quiz3k.controller;

import com.example.quiz3k.model.dao.QuizEntity;
import com.example.quiz3k.model.dto.CreateQuizRequest;
import com.example.quiz3k.model.dto.Question;
import com.example.quiz3k.model.dto.Quiz;
import com.example.quiz3k.service.QuizNotFoundException;
import com.example.quiz3k.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quiz")
@CrossOrigin(origins = "http://localhost:4200")
public class QuizController {

    private final QuizService quizService;

    @Autowired
    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @PostMapping
    public QuizEntity createQuiz (@RequestBody CreateQuizRequest body){
        return quizService.createQuiz(body.getQuizName());
    }

    @GetMapping
    public List<Quiz> getAllQuiz(){
        return quizService.getAllQuiz();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteQuiz(@PathVariable("id") Long id) {
        quizService.deleteQuiz(id);
        return ResponseEntity.noContent().build();
    }
}
