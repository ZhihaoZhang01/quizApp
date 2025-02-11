package com.zhihao.quizapp.model;

import lombok.Data;

/**
 * @author Zhihao Zhang
 * @description TODO
 * @date 2025-02-03 8:22 PM
 */

@Data
public class QuestionEditForm {
    private int questionId;
    private int categoryId;
    private String description;
    private int choice1Id;
    private String choice1;
    private int choice2Id;
    private String choice2;
    private int choice3Id;
    private String choice3;
    private int choice4Id;
    private String choice4;
    private int correctChoice;
}

