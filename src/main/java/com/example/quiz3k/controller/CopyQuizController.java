package com.example.quiz3k.controller;

import com.example.quiz3k.model.dao.CopyQuizEntity;
import com.example.quiz3k.repository.CopyQuizRepository;
import com.example.quiz3k.service.CopyQuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CopyQuizController {

    private final CopyQuizRepository copyRepo;
    private final CopyQuizService copyService;

    @Autowired
    public CopyQuizController(CopyQuizRepository copyRepo, CopyQuizService copyService) {
        this.copyRepo = copyRepo;
        this.copyService = copyService;
    }

//    @PostMapping
//    public CopyQuizEntity createCopyQuiz(@RequestBody )
}
