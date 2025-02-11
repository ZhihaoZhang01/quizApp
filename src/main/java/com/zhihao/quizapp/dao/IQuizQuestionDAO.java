package com.zhihao.quizapp.dao;

import com.zhihao.quizapp.model.QuizQuestion;

import java.util.List;

/**
 * @author Zhihao Zhang
 * @description TODO
 * @date 2025-02-08 11:50 PM
 */
public interface IQuizQuestionDAO {
    void addQuizQuestion(int quizId, int questionId);
    List<QuizQuestion> getQuizQuestionsByQuizId(int quizId);
    void updateUserChoice(int qqId, int userChoiceId);
}
