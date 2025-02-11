package com.zhihao.quizapp.dao.jdbc;

import com.zhihao.quizapp.dao.IQuestionDAO;
import com.zhihao.quizapp.dao.mapper.QuestionRowMapper;
import com.zhihao.quizapp.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Zhihao Zhang
 * @description TODO
 * @date 2025-01-31 10:41 PM
 */

@Repository("jdbcQuestionDAO")
public class QuestionDaoJdbcImpl implements IQuestionDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private QuestionRowMapper questionRowMapper;

    @Override
    public List<Question> getRandomQuestionsByCategory(int categoryId, int limit) {
        String query = "SELECT * FROM question WHERE category_id = ? AND is_active = 1 ORDER BY RAND() LIMIT ?";
        return jdbcTemplate.query(query, questionRowMapper, categoryId, limit);
    }
    @Override
    public Question getQuestionById(int questionId) {
        String query = "SELECT * FROM question WHERE question_id = ?";
        return jdbcTemplate.queryForObject(query, questionRowMapper, questionId);
    }
    @Override
    public List<Question> getQuestions(int offset, int limit) {
        String query = "SELECT * FROM question LIMIT ? OFFSET ?";
        return jdbcTemplate.query(query, questionRowMapper, limit, offset);
    }
    @Override
    public void updateQuestionStatus(int questionId, int newStatus) {
        String query = "UPDATE question SET is_active = ? WHERE question_id = ?";
        jdbcTemplate.update(query, newStatus, questionId);
    }
    @Override
    public Integer addQuestion(Question question) {
        String query = "INSERT INTO Question (category_id, description, is_active) VALUES (?, ?, ?)";
        jdbcTemplate.update(query, question.getCategory().getCategoryId(), question.getDescription(), question.getIsActive());
        return jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
    }
    @Override
    public void updateQuestion(Question question) {
        String query = "UPDATE Question SET category_id = ?, description = ? WHERE question_id = ?";
        jdbcTemplate.update(query, question.getCategory().getCategoryId(), question.getDescription(), question.getQuestionId());
    }

}
