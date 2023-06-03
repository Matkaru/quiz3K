package com.example.quiz3k.controller;

import com.example.quiz3k.enums.QuestionType;
import com.example.quiz3k.model.dao.QuestionEntity;
import com.example.quiz3k.model.dao.QuizEntity;
import com.example.quiz3k.model.dto.CreateQuestionRequest;
import com.example.quiz3k.service.QuestionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
//@CrossOrigin(origins = "http://localhost:4200")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(path = "/api/questions")
    public ResponseEntity<?> createQuestion(@RequestBody @Valid CreateQuestionRequest request) {

        QuestionEntity createdQuestion = questionService.createQuestion(request.getQuestionText(), request.getQuestionType());
        return ResponseEntity.status(HttpStatus.CREATED).body(createdQuestion);
    }

    @GetMapping(path = "/api/questions")
    public List<String> getAllQuestion(){
        return questionService.getAllQuestions();
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/api/questions/{id}")
    @DeleteMapping(path = "/api/questions/{id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable("id") Long id) {
        questionService.deleteQuestion(id);
        return ResponseEntity.noContent().build();
    }
}
