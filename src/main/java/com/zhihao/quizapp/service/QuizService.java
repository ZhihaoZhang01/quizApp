package com.zhihao.quizapp.service;

import com.zhihao.quizapp.dao.IChoiceDAO;
import com.zhihao.quizapp.dao.IQuestionDAO;
import com.zhihao.quizapp.dao.IQuizDAO;
import com.zhihao.quizapp.dao.IQuizQuestionDAO;
import com.zhihao.quizapp.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Zhihao Zhang
 * @description TODO
 * @date 2025-02-01 6:55 PM
 */


@Service
public class QuizService {
    @Autowired
    @Lazy
    private IQuizDAO quizDAO;
    @Autowired
    @Lazy
    private IQuestionDAO questionDAO;
    @Autowired
    @Lazy
    private IQuizQuestionDAO quizQuestionDAO;
    @Autowired
    @Lazy
    private IChoiceDAO choiceDAO;


    @Transactional
    public int createNewQuiz(int UserId, int categoryId, String quizName){
        int quizId = quizDAO.createQuiz(UserId, categoryId, quizName);
        List<Question> questions = questionDAO.getRandomQuestionsByCategory(categoryId,5);
        for (Question question : questions) {
            quizQuestionDAO.addQuizQuestion(quizId, question.getQuestionId());
        }
        return quizId;
    }

    @Transactional
    public List<QuizQuestionDetail> getQuizQuestionDetails(int quizId) {
        List<QuizQuestion> qqList = quizQuestionDAO.getQuizQuestionsByQuizId(quizId);
        List<QuizQuestionDetail> details = new ArrayList<>();
        for (QuizQuestion qq : qqList) {
            QuizQuestionDetail detail = new QuizQuestionDetail();
            Question question = questionDAO.getQuestionById(qq.getQuestion().getQuestionId());
            List<Choice> choices = choiceDAO.getChoicesByQuestionId(question.getQuestionId());
            detail.setQuizQuestion(qq);
            detail.setQuestion(question);
            detail.setChoices(choices);
            details.add(detail);
        }
        return details;
    }


    @Transactional
    public int getQuizScore(int quizId) {
        return quizDAO.getQuizScore(quizId);
    }


    @Transactional
    public long countTime(int quizId){
        Quiz quiz = quizDAO.getQuizById(quizId);
        long time = (System.currentTimeMillis() - quiz.getTimeStart().getTime())/1000;
        return 300 - time > 0 ? 300 - time : 0;
    }

    @Transactional
    public void updateEndTime(int quizId){
        quizDAO.updateQuizEndTime(quizId);
    }

    @Transactional
    public List<Quiz> getQuizByUserId(int userId){
        return quizDAO.getRecentQuizzesByUser(userId);
    }

}
