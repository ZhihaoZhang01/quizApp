package com.zhihao.quizapp.model;
import lombok.Data;

import java.util.List;
/**
 * @author Zhihao Zhang
 * @description TODO
 * @date 2025-02-01 8:15 PM
 */


@Data
public class QuizQuestionDetail {
    private QuizQuestion quizQuestion;
    private Question question;
    private List<Choice> choices;
}

