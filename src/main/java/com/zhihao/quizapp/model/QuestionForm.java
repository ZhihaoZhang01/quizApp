package com.zhihao.quizapp.model;

import lombok.Data;

/**
 * @author Zhihao Zhang
 * @description TODO
 * @date 2025-02-02 7:23 PM
 */
@Data
public class QuestionForm {
    private int categoryId;
    private String description;
    private String choice1;
    private String choice2;
    private String choice3;
    private String choice4;
    private int correctChoice;
}

