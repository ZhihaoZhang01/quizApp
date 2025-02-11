package com.zhihao.quizapp.dao.mapper;

import com.zhihao.quizapp.model.Category;
import com.zhihao.quizapp.model.Question;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Zhihao Zhang
 * @description TODO
 * @date 2025-01-31 10:41 PM
 */

@Component
public class QuestionRowMapper implements RowMapper<Question> {

    @Override
    public Question mapRow(ResultSet rs, int rowNum) throws SQLException {
        Question question = new Question();
        question.setDescription(rs.getString("description"));
        question.setIsActive(rs.getInt("is_active"));
        question.setQuestionId(rs.getInt("question_id"));
        Category category = new Category();
        category.setCategoryId(rs.getInt("category_id"));
        question.setCategory(category);
        return question;
    }
}
