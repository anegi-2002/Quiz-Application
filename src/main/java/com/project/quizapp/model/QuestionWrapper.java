package com.project.quizapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionWrapper {
    private Integer questionno;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String questiontitle;
}
