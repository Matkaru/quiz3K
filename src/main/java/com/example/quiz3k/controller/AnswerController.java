package com.example.quiz3k.controller;

import com.example.quiz3k.model.dao.AnswerEntity;
import com.example.quiz3k.model.dto.Answer;
import com.example.quiz3k.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/answers")
@CrossOrigin(origins = "http://localhost:4200")
public class AnswerController {

    private final AnswerService answerService;

    @Autowired
    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @PostMapping
    public ResponseEntity<?> createAnswer(@Validated @RequestBody Answer answer, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }

        AnswerEntity createdAnswer = answerService.createAnswer(answer);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAnswer);
    }

//    @GetMapping
//    public List<String> getAllAnswers() {
//        return answerService.getAllAnswers();
//    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteAnswer(@PathVariable("id") Long id) {
        answerService.deleteAnswer(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public List<String> getAnswersByQuestionId(@RequestParam("answerQuestionId") Long answerQuestionId) {
        return answerService.getAnswersByQuestionId(answerQuestionId);
    }
}
