package com.example.quiz3k.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class SharedAnswer {
    Long questionId;
    List<Long> answers;
}
