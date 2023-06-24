package com.example.quiz3k.model.dao;
import jakarta.persistence.*;
import lombok.Data;
import java.util.*;

@Table(name = "copy_quiz")
@Entity
@Data
public class CopyQuizEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long quizId;
    //jakie pytanie (questionId),
//    private Long answerQuestionId;

//    private Long answerId;
    @ElementCollection
    private List<Long> userAnswerIdList;


    public void addUserAnswerId(Long userAnswerId) {
        if (userAnswerIdList == null) {
            userAnswerIdList = new ArrayList<>();
        }
        userAnswerIdList.add(userAnswerId);
    }
}
