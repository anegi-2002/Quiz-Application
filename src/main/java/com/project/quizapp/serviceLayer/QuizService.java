package com.project.quizapp.serviceLayer;

import com.project.quizapp.model.*;
import com.project.quizapp.repositoryLayer.QuestionRepository;
import com.project.quizapp.repositoryLayer.QuizDao;
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
    QuizDao quizRepo;
    @Autowired
    QuestionRepository questionRepo;

    public ResponseEntity<List<QuestionEntity>> createQuiz(String cateogry, Integer noOfQuestion, String title) {
        List<QuestionEntity> question = questionRepo.findRandomQuestionsByCategory(cateogry,noOfQuestion);

        QuizEntity quiz=new QuizEntity();
        quiz.setTitle(title);
        quiz.setQuestions(question);
        quizRepo.save(quiz);
        return new ResponseEntity<>(question, HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuiz(Integer category) {
        Optional<QuizEntity> questionOb= quizRepo.findById(category);
        List<QuestionEntity> lisofQuestions = questionOb.get().getQuestions();
        List<QuestionWrapper> questionWrapperList = new ArrayList<>();
        QuestionWrapper qWrapper = new QuestionWrapper();
        if(questionOb.isPresent()){
            lisofQuestions.stream().forEach(it->{
                questionWrapperList.add(new QuestionWrapper(it.getQuestionno(),
                        it.getOption1(),
                        it.getOption2(),
                        it.getOption3(),
                        it.getOption4(),
                        it.getQuestiontitle()));
            });
//
            return new ResponseEntity<>(questionWrapperList, HttpStatus.OK);
        }

        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);

    }

    public ResponseEntity<List<QuizRightAnswerResponseEntity>> submitAnswers(Integer category, List<QuizSubmitEntity> submitOb) {
        Optional<QuizEntity> questionOb= quizRepo.findById(category);
        List<QuestionEntity> lisofQuestions = questionOb.get().getQuestions();
        List<QuizRightAnswerResponseEntity> anwerList = new ArrayList<>();

        submitOb.stream().forEach(it->{
            Optional<QuestionEntity> currentQuestionFromDb =   questionRepo.findById(it.getQuestionno());
            if(currentQuestionFromDb.isPresent()){
                QuestionEntity currentQuestion = currentQuestionFromDb.get();
                if(currentQuestion.getRightanswer().compareToIgnoreCase(it.getRightanswer())==0){
                    anwerList.add(new QuizRightAnswerResponseEntity(it.getQuestionno(), it.getRightanswer(), true));
                }else{
                    anwerList.add(new QuizRightAnswerResponseEntity(it.getQuestionno(), currentQuestion.getRightanswer(), false));
                }
            }

        });
        return new ResponseEntity<>(anwerList,HttpStatus.OK);
    }
}
