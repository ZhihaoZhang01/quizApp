package com.zhihao.quizapp.dao.mapper;


import com.zhihao.quizapp.model.Question;
import com.zhihao.quizapp.model.Quiz;
import com.zhihao.quizapp.model.Choice;
import com.zhihao.quizapp.model.QuizQuestion;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Zhihao Zhang
 * @description TODO
 * @date 2025-02-01 6:50 PM
 */

@Component
public class QuizQuestionRowMapper implements RowMapper<QuizQuestion> {

    @Override
    public QuizQuestion mapRow(ResultSet rs, int rowNum) throws SQLException{
        QuizQuestion qq = new QuizQuestion();
        qq.setQqId(rs.getInt("qq_id"));
        Quiz quiz = new Quiz();
        quiz.setQuizId(rs.getInt("quiz_id"));
        qq.setQuiz(quiz);
        Question question = new Question();
        question.setQuestionId(rs.getInt("question_id"));
        qq.setQuestion(question);
        int userChoiceId = rs.getInt("user_choice_id");
        if (!rs.wasNull()) {
            Choice userChoice = new Choice();
            userChoice.setChoiceId(userChoiceId);
            qq.setUserChoice(userChoice);
        }
        return qq;
    }
}
