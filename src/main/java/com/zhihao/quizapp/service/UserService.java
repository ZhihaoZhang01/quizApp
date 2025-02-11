package com.zhihao.quizapp.service;

import com.zhihao.quizapp.dao.IUserDAO;
import com.zhihao.quizapp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Zhihao Zhang
 * @description TODO
 * @date 2025-01-31 7:38 PM
 */

@Service
public class UserService {

    private final IUserDAO userDAO;

    @Autowired
    public UserService( @Lazy IUserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Transactional
    public User findUserByEmail(String email){
        return userDAO.findByEmail(email);
    }

    @Transactional
    public User findUserById(int id){
        return userDAO.findById(id);
    }

    @Transactional
    public List<User> getAllUsers(){
        return userDAO.getAllUsers();
    }

    @Transactional
    public boolean registerNewUser(User user){
        if(findUserByEmail(user.getEmail()) != null){
            return false;
        }
        userDAO.createUser(user);
        return true;
    }

    @Transactional
    public User login(String email, String password){
        User user = findUserByEmail(email);
        if(user != null && user.getPassword().equals(password)){
            return user;
        }
        return null;
    }

    @Transactional
    public void updateUserStatus(int userId, int newStatus) {
         userDAO.updateUserStatus(userId,newStatus);
    }

    @Transactional
    public void deleteUser(int userId) {
        userDAO.deleteUser(userId);
    }


}
