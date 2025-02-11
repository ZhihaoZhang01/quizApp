package com.zhihao.quizapp.dao.jdbc;

import com.zhihao.quizapp.dao.IChoiceDAO;
import com.zhihao.quizapp.dao.mapper.ChoiceRowMapper;
import com.zhihao.quizapp.model.Choice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Zhihao Zhang
 * @description TODO
 * @date 2025-01-31 10:45 PM
 */

@Repository("jdbcChoiceDAO")
public class ChoiceDaoJdbcImpl implements IChoiceDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ChoiceRowMapper choiceRowMapper;

    @Override
    public List<Choice> getChoicesByQuestionId(int questionId) {
        String query = "SELECT * FROM choice WHERE question_id = ?";
        return jdbcTemplate.query(query, choiceRowMapper, questionId);
    }
    @Override
    public void addChoice(Choice choice) {
        String query = "INSERT INTO Choice (question_id, description, is_correct) VALUES (?, ?, ?)";
        jdbcTemplate.update(query, choice.getQuestion().getQuestionId(), choice.getDescription(), choice.getIsCorrect());
    }
    @Override
    public void updateChoice(Choice choice) {
        String query = "UPDATE Choice SET description = ?, is_correct = ? WHERE choice_id = ?";
        jdbcTemplate.update(query, choice.getDescription(), choice.getIsCorrect(), choice.getChoiceId());
    }

    @Override
    public Choice getChoiceById(int choiceId) {
        return null;
    }
}
