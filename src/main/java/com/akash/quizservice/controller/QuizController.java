package com.akash.quizservice.controller;


import com.akash.quizservice.model.QuestionWrapper;
import com.akash.quizservice.model.QuizDto;
import com.akash.quizservice.model.Response;
import com.akash.quizservice.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestBody QuizDto quizDto){
        return quizService.createQuiz(quizDto.getCategoryName(),quizDto.getNumQuestions(),quizDto.getTitle());
    }

    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Integer id){
        return quizService.getQuizQuestions(id);
    }

        @PostMapping("submit/{id}")
    public ResponseEntity<Integer> calculateScore(@PathVariable Integer id, @RequestBody List<Response> responses){
        return quizService.calculateScore(id,responses);

    }


}
