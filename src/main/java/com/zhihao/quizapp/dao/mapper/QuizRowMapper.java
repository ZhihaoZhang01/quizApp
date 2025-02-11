package com.zhihao.quizapp.dao.mapper;

import com.zhihao.quizapp.model.Category;
import com.zhihao.quizapp.model.Quiz;
import com.zhihao.quizapp.model.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * @author Zhihao Zhang
 * @description TODO
 * @date 2025-01-31 11:00 PM
 */


@Component
public class QuizRowMapper implements RowMapper<Quiz> {
    @Override
    public Quiz mapRow(ResultSet rs, int rowNum) throws SQLException {
        Quiz quiz = new Quiz();
        User user = new User();
        Category category = new Category();
        quiz.setQuizId(rs.getInt("quiz_id"));
        user.setUserId(rs.getInt("user_id"));
        quiz.setUser(user);
        category.setCategoryId(rs.getInt("category_id"));
        quiz.setCategory(category);
        quiz.setName(rs.getString("name"));
        quiz.setTimeStart(rs.getTimestamp("time_start"));
        quiz.setTimeEnd(rs.getTimestamp("time_end"));
        return quiz;
    }
}
