package com.zhihao.quizapp.dao.hibernate;

import com.zhihao.quizapp.dao.ICategoryDAO;
import com.zhihao.quizapp.model.Category;
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
 * @date 2025-02-08 10:54 PM
 */

@Repository("hibernateCategoryImpl")
@Primary
public class CategoryDaoHibernateImpl implements ICategoryDAO {
    @Autowired
    private SessionFactory sessionFactory;

    private SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    private Session getCurrentSession() {
        return getSessionFactory().getCurrentSession();
    }

    @Override
    public List<Category> getAllCategories() {
        return getCurrentSession().createQuery("FROM Category", Category.class).list();
    }

    @Override
    public Category getCategoryById(int categoryId) {
        return getCurrentSession().get(Category.class, categoryId);
    }
}
