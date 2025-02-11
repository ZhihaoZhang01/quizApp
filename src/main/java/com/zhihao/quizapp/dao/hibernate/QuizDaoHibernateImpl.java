package com.zhihao.quizapp.dao.hibernate;

import com.zhihao.quizapp.dao.IQuizDAO;
import com.zhihao.quizapp.model.Category;
import com.zhihao.quizapp.model.Quiz;
import com.zhihao.quizapp.model.User;
import com.zhihao.quizapp.model.QuizHistory;
import lombok.var;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManagerFactory;
import java.util.Date;
import java.util.List;

/**
 * @author Zhihao Zhang
 * @description TODO
 * @date 2025-02-08 11:38 PM
 */

@Repository("hibernateQuizDAO")
@Primary
public class QuizDaoHibernateImpl implements IQuizDAO {

    @Autowired
    private SessionFactory sessionFactory;

    private SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    private Session getCurrentSession() {
        return getSessionFactory().getCurrentSession();
    }

    @Override
    public Quiz getQuizById(int quizId) {
        return getCurrentSession().get(Quiz.class, quizId);
    }

    @Override
    public List<Quiz> getRecentQuizzesByUser(int userId) {
        String hql = "FROM Quiz q WHERE q.user.userId = :userId AND q.timeEnd IS NOT NULL ORDER BY q.timeStart DESC";
        return getCurrentSession().createQuery(hql, Quiz.class)
                .setParameter("userId", userId)
                .list();
    }

    @Override
    public int createQuiz(int userId, int categoryId, String name) {
        Quiz quiz = new Quiz();
        User user = new User();
        user.setUserId(userId);
        quiz.setUser(user);
        Category category = new Category();
        category.setCategoryId(categoryId);
        quiz.setCategory(category);
        quiz.setName(name);
        quiz.setTimeStart(new Date());
        return (Integer) getCurrentSession().save(quiz);
    }

    @Override
    public int getQuizScore(int quizId) {
        String hql = "select count(*) from QuizQuestion qq join qq.userChoice c where qq.quiz.quizId = :quizId and c.isCorrect = 1";
        Long count = (Long) getCurrentSession().createQuery(hql)
                .setParameter("quizId", quizId)
                .uniqueResult();
        return count != null ? count.intValue() : 0;
    }

    @Override
    public void updateQuizEndTime(int quizId) {
        Quiz quiz = getQuizById(quizId);
        if(quiz != null) {
            quiz.setTimeEnd(new Date());
            getCurrentSession().update(quiz);
        }
    }

    @Override
    public Quiz getOpenQuizForUser(int userId) {
        String hql = "FROM Quiz q WHERE q.user.userId = :userId AND q.timeEnd IS NULL";
        return getCurrentSession().createQuery(hql, Quiz.class)
                .setParameter("userId", userId)
                .setMaxResults(1)
                .uniqueResult();
    }

    @Override
    public List<QuizHistory> getQuizHistories(int offset, int limit, String category, String userEmail) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT q.quiz_id AS QUIZ_ID, ");
        sql.append("CONCAT(u.firstname, q.quiz_id) AS QUIZNAME, ");
        sql.append("c.name AS CATEGORYNAME, ");
        sql.append("DATE_FORMAT(q.time_start, '%Y-%m-%d %H:%i:%s') AS STARTTIME, ");
        sql.append("CASE WHEN q.time_end IS NOT NULL THEN DATE_FORMAT(q.time_end, '%Y-%m-%d %H:%i:%s') ELSE 'Pending' END AS ENDTIME, ");
        sql.append("(SELECT COUNT(*) FROM QuizQuestion qq JOIN Choice c2 ON qq.user_choice_id = c2.choice_id WHERE qq.quiz_id = q.quiz_id AND c2.is_correct = 1) AS SCORE, ");
        sql.append("CASE WHEN (SELECT COUNT(*) FROM QuizQuestion qq JOIN Choice c2 ON qq.user_choice_id = c2.choice_id WHERE qq.quiz_id = q.quiz_id AND c2.is_correct = 1) > 3 THEN 'Pass' ELSE 'Fail' END AS RESULT, ");
        sql.append("CONCAT(u.firstname, ' ', u.lastname) AS USERNAME ");
        sql.append("FROM Quiz q ");
        sql.append("JOIN User u ON q.user_id = u.user_id ");
        sql.append("JOIN Category c ON q.category_id = c.category_id ");

        boolean whereAdded = false;
        if (category != null && !category.isEmpty()) {
            sql.append("WHERE c.name = :category ");
            whereAdded = true;
        }
        if (userEmail != null && !userEmail.isEmpty()) {
            if (whereAdded) {
                sql.append("AND u.email = :userEmail ");
            } else {
                sql.append("WHERE u.email = :userEmail ");
            }
        }
        sql.append("ORDER BY q.time_end DESC LIMIT :limit OFFSET :offset");

        var query = getCurrentSession().createNativeQuery(sql.toString(), "QuizHistoryMapping")
                .setParameter("offset", offset)
                .setParameter("limit", limit);

        if (category != null && !category.isEmpty()) {
            query.setParameter("category", category);
        }
        if (userEmail != null && !userEmail.isEmpty()) {
            query.setParameter("userEmail", userEmail);
        }
        return query.list();
    }
}
