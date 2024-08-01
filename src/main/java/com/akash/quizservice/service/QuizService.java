package com.akash.quizservice.service;


import com.akash.quizservice.dao.QuizDao;
import com.akash.quizservice.feign.QuizInterface;
import com.akash.quizservice.model.Question;
import com.akash.quizservice.model.QuestionWrapper;
import com.akash.quizservice.model.Quiz;
import com.akash.quizservice.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;

    @Autowired
    QuizInterface quizInterface;
        public ResponseEntity<String> createQuiz(String category,Integer numQ, String title ){

        List<Integer> questions = quizInterface.getQuestionsforQuiz(category,numQ).getBody();
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionIds(questions);
        quizDao.save(quiz);

        return new ResponseEntity<>("Success",HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        Optional<Quiz> quiz = quizDao.findById(id);
        List<Integer> questionIds = quiz.get().getQuestionIds();
        return quizInterface.getQuestionsFromId(questionIds);
    }

    public ResponseEntity<Integer> calculateScore(Integer id, List<Response> responses) {

        return quizInterface.getScore(responses);
    }
}
