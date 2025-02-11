package com.zhihao.quizapp.dao;

import com.zhihao.quizapp.model.Question;

import java.util.List;

/**
 * @author Zhihao Zhang
 * @description TODO
 * @date 2025-02-08 11:27 PM
 */
public interface IQuestionDAO {
     List<Question> getRandomQuestionsByCategory(int categoryId, int limit);
     Question getQuestionById(int questionId);
     List<Question> getQuestions(int offset, int limit);
     void updateQuestionStatus(int questionId, int newStatus);
     Integer addQuestion(Question question);
     void updateQuestion(Question question);
}
