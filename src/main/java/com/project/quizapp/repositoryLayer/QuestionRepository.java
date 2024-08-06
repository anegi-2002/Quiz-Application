package com.project.quizapp.repositoryLayer;

import com.project.quizapp.model.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<QuestionEntity, Integer> // jparepsotoriy uses calssname which maps to the table and primary key<quizEntity,Interger>
{
List<QuestionEntity> findByCategoryIgnoreCase(String category);

@Query(value = "SELECT * FROM quizquestions2 q WHERE q.category=:category ORDER BY RANDOM() LIMIT :noOfQuestion",nativeQuery = true)
    List<QuestionEntity> findRandomQuestionsByCategory(String category, Integer noOfQuestion);
}
