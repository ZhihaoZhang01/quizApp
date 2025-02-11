package com.zhihao.quizapp.dao;

import com.zhihao.quizapp.model.Choice;

import java.util.List;

/**
 * @author Zhihao Zhang
 * @description TODO
 * @date 2025-02-08 11:19 PM
 */
public interface IChoiceDAO {
    List<Choice> getChoicesByQuestionId(int questionId);
    void addChoice(Choice choice);
    void updateChoice(Choice choice);
    Choice getChoiceById(int choiceId);

}
