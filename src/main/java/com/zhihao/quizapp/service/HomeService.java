package com.zhihao.quizapp.service;

import com.zhihao.quizapp.dao.ICategoryDAO;
import com.zhihao.quizapp.dao.IQuizDAO;

import com.zhihao.quizapp.model.Category;
import com.zhihao.quizapp.model.Quiz;
import com.zhihao.quizapp.model.QuizHistory;
import com.zhihao.quizapp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Zhihao Zhang
 * @description TODO
 * @date 2025-02-01 8:23 PM
 */
@Service
public class HomeService {

    @Autowired
    @Lazy
    private IQuizDAO quizDAO;

    @Autowired
    @Lazy
    private ICategoryDAO categoryDAO;

    @Autowired
    private QuizService quizService;


    @Transactional
    public List<QuizHistory> getQuizHistoryForUser(int userId, User user) {
        List<QuizHistory> histories = new ArrayList<>();
        List<Quiz> recentQuizzes = quizDAO.getRecentQuizzesByUser(userId);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for(Quiz quiz : recentQuizzes){
            QuizHistory quizHistory = new QuizHistory();
            quizHistory.setQuizId(quiz.getQuizId());
            quizHistory.setCategoryName(quiz.getName());
            Category category = categoryDAO.getCategoryById(quiz.getCategory().getCategoryId());
            quizHistory.setCategoryName(category.getName());
            if (quiz.getTimeStart() != null) {
                quizHistory.setStartTime(sdf.format(quiz.getTimeStart()));
            }
            if (quiz.getTimeEnd() != null) {
                quizHistory.setEndTime(sdf.format(quiz.getTimeEnd()));
            } else {
                quizHistory.setEndTime("Pending");
            }
            int score = quizService.getQuizScore(quiz.getQuizId());
            quizHistory.setScore(score);
            quizHistory.setResult(score > 3 ?"Pass" : "Fail");
            quizHistory.setUserName(user.getFirstname()  + " " + user.getLastname());
            quizHistory.setQuizName(user.getFirstname() + quiz.getQuizId());
            histories.add(quizHistory);
        }
        return histories;
    }

    @Transactional
    public QuizHistory getQuizHistoryByQuizId(int quizId, User user) {
        Quiz quiz = quizDAO.getQuizById(quizId);
        QuizHistory history = new QuizHistory();
        history.setQuizId(quiz.getQuizId());
        history.setQuizName(quiz.getName());
        Category category = categoryDAO.getCategoryById(quiz.getCategory().getCategoryId());
        history.setCategoryName(category.getName());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (quiz.getTimeStart() != null) {
            history.setStartTime(sdf.format(quiz.getTimeStart()));
        }
        if (quiz.getTimeEnd() != null) {
            history.setEndTime(sdf.format(quiz.getTimeEnd()));
        } else {
            history.setEndTime("Pending");
        }
        int score = quizService.getQuizScore(quiz.getQuizId());
        history.setScore(score);
        history.setResult(score >= 3 ? "Pass" : "Fail");
        history.setUserName(user.getFirstname() + " " + user.getLastname());
        history.setQuizName(user.getFirstname() + quiz.getQuizId());
        return history;
    }

    @Transactional
    public Quiz getOpenQuizForUser(int userId){
        return quizDAO.getOpenQuizForUser(userId);
    }
}
