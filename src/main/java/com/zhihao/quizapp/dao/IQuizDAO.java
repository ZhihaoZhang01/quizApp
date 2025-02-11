package com.zhihao.quizapp.dao;

import com.zhihao.quizapp.model.Quiz;
import com.zhihao.quizapp.model.QuizHistory;

import java.util.List;

/**
 * @author Zhihao Zhang
 * @description TODO
 * @date 2025-02-08 11:34 PM
 */
public interface IQuizDAO {
    Quiz getQuizById(int quizId);
     List<Quiz> getRecentQuizzesByUser(int user_id);
     int createQuiz(int userId, int categoryId, String name);
     int getQuizScore(int quizId);

     void updateQuizEndTime(int quizId);

     Quiz getOpenQuizForUser(int userId);

     List<QuizHistory> getQuizHistories(int offset, int limit, String category, String userEmail);
}
