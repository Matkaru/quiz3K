package com.example.quiz3k.controller;

import com.example.quiz3k.model.dto.QuizData;
import com.example.quiz3k.repository.CopyQuizRepository;
import com.example.quiz3k.service.CopyQuizService;
import com.example.quiz3k.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CopyQuizController {

    private final CopyQuizRepository copyRepo;
    private final CopyQuizService copyService;
    private final QuestionService questionService;

    @Autowired
    public CopyQuizController(CopyQuizRepository copyRepo, CopyQuizService copyService, QuestionService questionService) {
        this.copyRepo = copyRepo;
        this.copyService = copyService;
        this.questionService = questionService;

    }
    @PostMapping(path = "/api/share/quiz/quizzes")
    public ResponseEntity<?> saveQuiz(@RequestBody QuizData quizData) {

        System.out.println("Odebrano quizData:");
        System.out.println("Nazwa Quizu: " + quizData.getQuizName());
        System.out.println("ID Quizu: " + quizData.getQuizId());
        System.out.println("Email: " + quizData.getEmail());


        return ResponseEntity.ok("Quiz został zapisany pomyślnie");
    }
}
