package com.project.quizapp.repositoryLayer;

import com.project.quizapp.model.QuizEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizDao extends JpaRepository<QuizEntity,Integer> {
}
