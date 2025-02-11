package com.zhihao.quizapp.dao.mapper;

import com.zhihao.quizapp.model.Choice;
import com.zhihao.quizapp.model.Question;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Zhihao Zhang
 * @description TODO
 * @date 2025-01-31 10:46 PM
 */
@Component
public class ChoiceRowMapper implements RowMapper<Choice> {
    @Override
    public Choice mapRow(ResultSet rs, int rowNum) throws SQLException {
        Choice choice = new Choice();
        choice.setChoiceId(rs.getInt("choice_id"));
        choice.setDescription(rs.getString("description"));
        choice.setIsCorrect(rs.getInt("is_correct"));
        Question question = new Question();
        question.setQuestionId(rs.getInt("question_id"));
        choice.setQuestion(question);
        return choice;
    }
}
