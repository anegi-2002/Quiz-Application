package com.project.quizapp.controllerlayer;

import com.project.quizapp.model.QuestionEntity;
import com.project.quizapp.serviceLayer.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class questionsController {

   @Autowired
   public QuestionService qs;

   @GetMapping("allquestions")
   public ResponseEntity<List<QuestionEntity>> getAllQuestions() throws Exception {
      return qs.getAllQuestions();
   }

   @PostMapping("save")
   public String saveQuestion(@RequestBody QuestionEntity qz){
      return qs.saveQuiz(qz);
   }
   @DeleteMapping("deleteQuiz/{id}")
   public String deleteQuizQuestion(@PathVariable("id")Integer questionno){
      qs.deleteQuizQuestion(questionno);
      return "";
   }
   @GetMapping("questions/{id}")
   public List<QuestionEntity> getQuestionListByID(@PathVariable("id")String category){
      return qs.getQuestionBycategory(category);
   }

}
