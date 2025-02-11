package com.zhihao.quizapp.dao;

import com.zhihao.quizapp.model.User;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;

/**
 * @author Zhihao Zhang
 * @description TODO
 * @date 2025-02-08 11:44 PM
 */
public interface IUserDAO {
    User findByEmail(String email);

    void createUser(User user);

    List<User> getUsers(int offset, int limit);

    List<User> getAllUsers();

    User findById(int userId);

    void updateUserStatus(int userId, int newStatus);
    void deleteUser(int userId);
}
