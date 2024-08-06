package com.project.quizapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuizRightAnswerResponseEntity {
    private Integer questionno;
    private String rightanswer;
    private Boolean isAnswerRight;

}
