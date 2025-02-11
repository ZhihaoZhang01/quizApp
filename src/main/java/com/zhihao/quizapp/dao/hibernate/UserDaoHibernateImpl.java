package com.zhihao.quizapp.dao.hibernate;

import com.zhihao.quizapp.dao.IUserDAO;
import com.zhihao.quizapp.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManagerFactory;
import java.util.List;

/**
 * @author Zhihao Zhang
 * @description TODO
 * @date 2025-02-08 11:49 PM
 */
@Repository("hibernateUserDAO")
@Primary
public class UserDaoHibernateImpl implements IUserDAO {
    @Autowired
    private EntityManagerFactory emf;

    private SessionFactory getSessionFactory() {
        return emf.unwrap(SessionFactory.class);
    }

    private Session getCurrentSession() {
        return getSessionFactory().getCurrentSession();
    }

    @Override
    public User findByEmail(String email) {
        String hql = "FROM User u WHERE u.email = :email";
        return getCurrentSession().createQuery(hql, User.class)
                .setParameter("email", email)
                .uniqueResult();
    }

    @Override
    public void createUser(User user) {
        getCurrentSession().save(user);
    }

    @Override
    public List<User> getUsers(int offset, int limit) {
        String hql = "FROM User";
        return getCurrentSession().createQuery(hql, User.class)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .list();
    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public User findById(int userId) {
        return getCurrentSession().get(User.class, userId);
    }

    @Override
    public void updateUserStatus(int userId, int newStatus) {
        User user = findById(userId);
        if (user != null) {
            user.setIsActive(newStatus);
            getCurrentSession().update(user);
        }
    }

    @Override
    public void deleteUser(int userId) {

    }
}
