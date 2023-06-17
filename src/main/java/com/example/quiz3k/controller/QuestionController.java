package com.example.quiz3k.controller;

import com.example.quiz3k.model.dao.QuestionEntity;
import com.example.quiz3k.model.dao.QuizEntity;
import com.example.quiz3k.model.dto.CreateQuestionRequest;
import com.example.quiz3k.repository.QuestionRepository;
import com.example.quiz3k.service.QuestionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class QuestionController {

    private final QuestionService questionService;
    private final QuestionRepository questionRepository;

    public QuestionController(QuestionService questionService, QuestionRepository questionRepository) {
        this.questionService = questionService;
        this.questionRepository = questionRepository;
    }

    @PostMapping(path = "/api/questions")
    public ResponseEntity<?> createQuestion(@RequestBody @Valid CreateQuestionRequest request) {

        QuestionEntity createdQuestion = questionService.createQuestion(request.getQuestionText(), request.getQuestionType());
        createdQuestion.setQuestionQuizId(request.getQuestionQuizId());
        questionRepository.save(createdQuestion);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdQuestion);
    }

    @GetMapping(path = "/api/questions")
    public List<String> getAllQuestion(@RequestParam("questionQuizId") Long questionQuizId) {
        return questionService.getQuestionsByQuizId(questionQuizId);
    }

    @DeleteMapping(path = "/api/questions/{id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable("id") Long id) {
        questionService.deleteQuestion(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping(path = "/api/questions/{id}")
    public ResponseEntity<QuestionEntity> updateQuestion(@PathVariable("id") Long id, @RequestBody @Valid CreateQuestionRequest request) {
        Optional<QuestionEntity> optionalQuestion = questionService.getQuestionById(id);
        if (optionalQuestion.isPresent()) {
            QuestionEntity question = optionalQuestion.get();
            question.setQuestionText(request.getQuestionText());
            question.setQuestionType(request.getQuestionType());
            QuestionEntity updatedQuestion = questionService.updateQuestion(question);
            return ResponseEntity.ok(updatedQuestion);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
