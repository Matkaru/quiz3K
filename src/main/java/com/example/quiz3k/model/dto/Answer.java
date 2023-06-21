package com.example.quiz3k.model.dto;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Answer {
    private Long id;
    @NotEmpty(message = "musi zawierac tekst")
    private String answerForTheQuestion;
    private boolean confirmedAnswer;
    private Long answerQuestionId;

}