package com.zhihao.quizapp.dao.hibernate;

import com.zhihao.quizapp.dao.IQuestionDAO;
import com.zhihao.quizapp.model.Question;
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
 * @date 2025-02-08 11:29 PM
 */
@Repository("hibernateQuestionDAO")
@Primary
public class QuestionDaoHibernateImpl implements IQuestionDAO {

    @Autowired
    private EntityManagerFactory emf;

    private SessionFactory getSessionFactory() {
        return emf.unwrap(SessionFactory.class);
    }

    private Session getCurrentSession() {
        return getSessionFactory().getCurrentSession();
    }

    @Override
    public List<Question> getRandomQuestionsByCategory(int categoryId, int limit) {
        String sql = "SELECT * FROM question WHERE category_id = ? AND is_active = 1 ORDER BY RAND() LIMIT ?";
        return getCurrentSession().createNativeQuery(sql, Question.class)
                .setParameter(1, categoryId)
                .setParameter(2, limit)
                .list();
    }

    @Override
    public Question getQuestionById(int questionId) {
        return getCurrentSession().get(Question.class, questionId);
    }

    @Override
    public List<Question> getQuestions(int offset, int limit) {
        String hql = "FROM Question";
        return getCurrentSession().createQuery(hql, Question.class)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .list();
    }

    @Override
    public void updateQuestionStatus(int questionId, int newStatus) {
        Question question = getQuestionById(questionId);
        if (question != null) {
            question.setIsActive(newStatus);
            getCurrentSession().update(question);
        }
    }

    @Override
    public Integer addQuestion(Question question) {
        return (Integer) getCurrentSession().save(question);
    }

    @Override
    public void updateQuestion(Question question) {
        getCurrentSession().update(question);
    }
}
