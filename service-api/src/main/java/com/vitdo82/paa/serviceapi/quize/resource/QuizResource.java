package com.vitdo82.paa.serviceapi.quize.resources;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/quiz")
public class QuizResource {

    @GetMapping("/{id}")
    public QuizeDTO.QuizResponse getQuiz(@PathVariable("id") String id) {
        return null;
    }

    @GetMapping
    public List<QuizeDTO.QuizResponse> getQuizzes() {
        return List.of();
    }
}
