package com.project.quizapp.controllerlayer;

import com.project.quizapp.model.QuestionEntity;
import com.project.quizapp.model.QuestionWrapper;
import com.project.quizapp.model.QuizRightAnswerResponseEntity;
import com.project.quizapp.model.QuizSubmitEntity;
import com.project.quizapp.serviceLayer.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    QuizService sq;

    @PostMapping("create")
    public ResponseEntity<List<QuestionEntity>> createQuizData(@RequestParam("category") String cateogry,
                                                            @RequestParam("noOfQuestion") Integer noOfQuestion,
                                                            @RequestParam("title") String title){
        return sq.createQuiz(cateogry,noOfQuestion,title);
    }
    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizBasisOfCategory(@PathVariable("id") Integer category){
        return sq.getQuiz(category);
    }
    @PostMapping("submit/{id}")
    public ResponseEntity<List<QuizRightAnswerResponseEntity>> submitAnswerOfQuiz(@PathVariable("id") Integer category,
                                                                                  @RequestBody List<QuizSubmitEntity> submitOb){
        return sq.submitAnswers(category,submitOb);
    }
}
