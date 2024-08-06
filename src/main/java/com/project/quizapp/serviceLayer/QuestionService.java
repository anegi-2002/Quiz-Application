package com.project.quizapp.serviceLayer;

import com.project.quizapp.model.QuestionEntity;
import com.project.quizapp.repositoryLayer.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    @Autowired
    QuestionRepository repOb;

    public ResponseEntity<List<QuestionEntity>> getAllQuestions() throws Exception{
        try {
            return new ResponseEntity<>(repOb.findAll(), HttpStatus.OK);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public String saveQuiz(QuestionEntity qz){
        repOb.save(qz);
        return "saved successfully";
    }
    public void deleteQuizQuestion(Integer questionno){
        Optional<QuestionEntity> qe = repOb.findById(questionno);
        if(qe.isPresent()){
            repOb.delete(qe.get());
        }
    }
    public List<QuestionEntity> getQuestionBycategory(String category){
        return repOb.findByCategoryIgnoreCase(category);
    }
}
