package com.zhihao.quizapp.dao.jdbc;

import com.zhihao.quizapp.dao.IQuizDAO;
import com.zhihao.quizapp.dao.mapper.QuizRowMapper;
import com.zhihao.quizapp.model.Quiz;
import com.zhihao.quizapp.model.QuizHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Zhihao Zhang
 * @description TODO
 * @date 2025-01-31 11:00 PM
 */

@Repository("jdbcQuizDAO")
public class QuizDaoJdbcImpl implements IQuizDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    QuizRowMapper quizRowMapper;

    @Override
    public Quiz getQuizById(int quizId){
        String query = "SELECT * FROM Quiz WHERE quiz_id = ?";
        return jdbcTemplate.queryForObject(query,quizRowMapper,quizId);
    }
    @Override
    public List<Quiz> getRecentQuizzesByUser(int user_id){
        String query = "SELECT * FROM Quiz WHERE user_id = ? AND time_end IS NOT NULL ORDER BY time_start DESC";
        return jdbcTemplate.query(query, quizRowMapper, user_id);
    }
    @Override
    public int createQuiz(int userId, int categoryId, String name){
        String query = "INSERT INTO Quiz (user_id, category_id, name) VALUES (?, ?, ?)";
        jdbcTemplate.update(query, userId, categoryId, name);
        return jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
    }

    @Override
    public int getQuizScore(int quizId){
        String query = "SELECT COUNT(*) FROM QuizQuestion qq " +
                "JOIN Choice c ON qq.user_choice_id = c.choice_id " +
                "WHERE qq.quiz_id = ? AND c.is_correct = 1";
        Integer score = jdbcTemplate.queryForObject(query, Integer.class, quizId);
        return score == null ? 0 : score;
    }
    @Override
    public void updateQuizEndTime(int quizId) {
        String query = "UPDATE Quiz SET time_end = CURRENT_TIMESTAMP WHERE quiz_id = ?";
        jdbcTemplate.update(query, quizId);
    }
    @Override
    public Quiz getOpenQuizForUser(int userId) {
        String query = "SELECT * FROM Quiz WHERE user_id = ? AND time_end IS NULL LIMIT 1";
        try {
            return jdbcTemplate.queryForObject(query, quizRowMapper, userId);
        } catch (Exception e) {
            return null;
        }
    }
    @Override
    public List<QuizHistory> getQuizHistories(int offset, int limit, String category, String userEmail) {
        StringBuilder query = new StringBuilder();
        query.append("SELECT q.quiz_id, ");
        query.append("CONCAT(u.firstname, q.quiz_id) AS quizName, ");
        query.append("c.name AS categoryName, ");
        query.append("DATE_FORMAT(q.time_start, '%Y-%m-%d %H:%i:%s') AS startTime, ");
        query.append("CASE WHEN q.time_end IS NOT NULL THEN DATE_FORMAT(q.time_end, '%Y-%m-%d %H:%i:%s') ELSE 'Pending' END AS endTime, ");
        query.append("(SELECT COUNT(*) FROM QuizQuestion qq JOIN Choice c2 ON qq.user_choice_id = c2.choice_id WHERE qq.quiz_id = q.quiz_id AND c2.is_correct = 1) AS score, ");
        query.append("CASE WHEN (SELECT COUNT(*) FROM QuizQuestion qq JOIN Choice c2 ON qq.user_choice_id = c2.choice_id WHERE qq.quiz_id = q.quiz_id AND c2.is_correct = 1) > 3 THEN 'Pass' ELSE 'Fail' END AS result, ");
        query.append("CONCAT(u.firstname, ' ', u.lastname) AS userName ");
        query.append("FROM Quiz q ");
        query.append("JOIN User u ON q.user_id = u.user_id ");
        query.append("JOIN Category c ON q.category_id = c.category_id ");

        boolean whereAdded = false;
        if (category != null && !category.isEmpty()) {
            query.append("WHERE c.name = ? ");
            whereAdded = true;
        }
        if (userEmail != null && !userEmail.isEmpty()) {
            if (whereAdded) {
                query.append("AND u.email = ? ");
            } else {
                query.append("WHERE u.email = ? ");
            }
        }
        query.append("ORDER BY q.time_end DESC LIMIT ? OFFSET ?");
        Object[] params;
        if (category != null && !category.isEmpty() && userEmail != null && !userEmail.isEmpty()) {
            params = new Object[]{category, userEmail, limit, offset};
        } else if (category != null && !category.isEmpty()) {
            params = new Object[]{category, limit, offset};
        } else if (userEmail != null && !userEmail.isEmpty()) {
            params = new Object[]{userEmail, limit, offset};
        } else {
            params = new Object[]{limit, offset};
        }
        return jdbcTemplate.query(query.toString(), new BeanPropertyRowMapper<>(QuizHistory.class), params);
    }



}
