package com.zhihao.quizapp.dao.jdbc;

import com.zhihao.quizapp.dao.IUserDAO;
import com.zhihao.quizapp.dao.mapper.UserRowMapper;
import com.zhihao.quizapp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Zhihao Zhang
 * @description TODO
 * @date 2025-01-31 7:15 PM
 */

@Repository("jdbcUserDAO")
public class UserDaoJdbcImpl implements IUserDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    UserRowMapper userRowMapper ;

    @Override
    public User findByEmail(String email) {
        String query = "SELECT * FROM User WHERE email = ?";
        try {
            return jdbcTemplate.queryForObject(query, userRowMapper, email);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    @Override
    public void createUser(User user){
        String query = "INSERT INTO user (email, password, firstname, lastname) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(query,user.getEmail(),user.getPassword(),user.getFirstname(),user.getLastname());
    }
    @Override
    public List<User> getUsers(int offset, int limit) {
        String query = "SELECT * FROM User LIMIT ? OFFSET ?";
        return jdbcTemplate.query(query, userRowMapper, limit, offset);
    }

    @Override
    public List<User> getAllUsers() {
        String query = "SELECT * FROM User";
        return jdbcTemplate.query(query, userRowMapper);
    }

    @Override
    public User findById(int userId) {
        String query = "SELECT * FROM User WHERE user_id = ?";
        return jdbcTemplate.queryForObject(query, userRowMapper, userId);
    }
    @Override
    public void updateUserStatus(int userId, int newStatus) {
        String query = "UPDATE User SET is_active = ? WHERE user_id = ?";
        jdbcTemplate.update(query, newStatus, userId);
    }

    @Override
    public void deleteUser(int userId) {
        String query = "DELETE FROM User WHERE user_id = ?";
        jdbcTemplate.update(query, userId);
    }
}
