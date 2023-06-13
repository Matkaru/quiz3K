package com.example.quiz3k.controller;

import com.example.quiz3k.enums.UserType;
import com.example.quiz3k.model.dao.QuizEntity;
import com.example.quiz3k.model.dao.UserEntity;
import com.example.quiz3k.model.dto.CreateQuizRequest;
import com.example.quiz3k.model.dto.Quiz;
import com.example.quiz3k.repository.UserRepository;
import com.example.quiz3k.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quiz")
@CrossOrigin(origins = "http://localhost:4200")
public class QuizController {

    private final QuizService quizService;
    private final UserRepository userRepository;

    @Autowired
    public QuizController(QuizService quizService, UserRepository userRepository) {
        this.quizService = quizService;
        this.userRepository = userRepository;
    }

    @PostMapping
    public QuizEntity createQuiz(@RequestBody CreateQuizRequest body) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String ownerEmail = authentication.getName();
        return quizService.createQuiz(body.getQuizName(), ownerEmail);
    }


    @GetMapping
    public List<Quiz> getAllQuiz() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String ownerEmail = authentication.getName();

        UserEntity userEntity = userRepository.findByEmail(ownerEmail)
                .orElseThrow(() -> new RuntimeException("Nie znaleziono użytkownika"));

        UserType userType = userEntity.getUserType();

        if (userType == UserType.ADMIN) {
            return quizService.getAllQuiz();
        } else {
            return quizService.getQuizzesForUser(ownerEmail);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteQuiz(@PathVariable("id") Long id) {
        quizService.deleteQuiz(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateQuiz(@PathVariable Long id, @RequestBody Quiz quizDTO) {
        quizService.updateQuiz(id, quizDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getQuiz(@PathVariable Long id) {
        QuizEntity quiz = quizService.getQuizById(id);

        if (quiz != null) {
            return ResponseEntity.ok(quiz);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
