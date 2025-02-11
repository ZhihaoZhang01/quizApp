package com.zhihao.quizapp.dao.hibernate;

import com.zhihao.quizapp.dao.IChoiceDAO;
import com.zhihao.quizapp.model.Choice;
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
 * @date 2025-02-08 11:22 PM
 */

@Repository("hibernateChoiceDAO")
@Primary
public class ChoiceDaoHibernateImpl implements IChoiceDAO {

    @Autowired
    private EntityManagerFactory emf;

    private SessionFactory getSessionFactory() {
        return emf.unwrap(SessionFactory.class);
    }

    private Session getCurrentSession() {
        return getSessionFactory().getCurrentSession();
    }

    @Override
    public List<Choice> getChoicesByQuestionId(int questionId) {
        String hql = "FROM Choice c WHERE c.question.questionId = :questionId";
        return getCurrentSession().createQuery(hql, Choice.class)
                .setParameter("questionId", questionId)
                .list();
    }

    @Override
    public void addChoice(Choice choice) {
        getCurrentSession().save(choice);
    }

    @Override
    public void updateChoice(Choice choice) {
        getCurrentSession().update(choice);
    }
}