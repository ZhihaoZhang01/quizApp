package com.zhihao.quizapp.dao.jdbc;

import com.zhihao.quizapp.dao.IQuizQuestionDAO;
import com.zhihao.quizapp.dao.mapper.QuizQuestionRowMapper;
import com.zhihao.quizapp.model.QuizQuestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
    @author Zhihao Zhang
    @description TODO
    @date 2025-01-31 11:39 PM
*/

@Repository("jdbcQuizQuestionDAO")
public class QuizQuestionDaoJdbcImpl implements IQuizQuestionDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private QuizQuestionRowMapper quizQuestionRowMapper;

    @Override
    public void addQuizQuestion(int quizId, int questionId) {
        String query = "INSERT INTO QuizQuestion (quiz_id, question_id) VALUES (?, ?)";
        jdbcTemplate.update(query, quizId, questionId);
    }
    @Override
    public List<QuizQuestion> getQuizQuestionsByQuizId(int quizId) {
        String query = "SELECT * FROM QuizQuestion WHERE quiz_id = ?";
        return jdbcTemplate.query(query, quizQuestionRowMapper, quizId);
    }
    @Override
    public void updateUserChoice(int qqId, int userChoiceId) {
        String query = "UPDATE QuizQuestion SET user_choice_id = ? WHERE qq_id = ?";
        jdbcTemplate.update(query, userChoiceId, qqId);
    }


}
