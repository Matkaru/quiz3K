package com.example.quiz3k.controller;

import com.example.quiz3k.model.dao.AnswerEntity;
import com.example.quiz3k.model.dto.Answer;
import com.example.quiz3k.model.dto.CreateAnswerRequest;
import com.example.quiz3k.service.AnswerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteAnswer(@PathVariable("id") Long id) {
        answerService.deleteAnswer(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public List<AnswerEntity> getAnswersByQuestionId(@RequestParam("answerQuestionId") Long answerQuestionId) {
        return answerService.getAnswersByQuestionId(answerQuestionId);
    }
    @PutMapping("/{id}")
    public ResponseEntity<AnswerEntity> updateAnswer(@PathVariable("id") Long id, @RequestBody @Valid CreateAnswerRequest request) {
        Optional<AnswerEntity> optionalAnswer = answerService.getAnswerById(id);
        if (optionalAnswer.isPresent()) {
            AnswerEntity answer = optionalAnswer.get();
            answer.setAnswerForTheQuestion(request.getAnswerForTheQuestion());
            answer.setConfirmedAnswer(request.isConfirmedAnswer());
            AnswerEntity updatedAnswer = answerService.updateAnswer(answer);
            return ResponseEntity.ok(updatedAnswer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
