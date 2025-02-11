package com.zhihao.quizapp.dao.hibernate;

import com.zhihao.quizapp.dao.IQuizQuestionDAO;
import com.zhihao.quizapp.model.Choice;
import com.zhihao.quizapp.model.Question;
import com.zhihao.quizapp.model.Quiz;
import com.zhihao.quizapp.model.QuizQuestion;
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
 * @date 2025-02-08 11:52 PM
 */
@Repository("hibernateQuizQuestionDAO")
@Primary
public class QuizQuestionDaoHibernateImpl implements IQuizQuestionDAO {

    @Autowired
    private EntityManagerFactory emf;

    private SessionFactory getSessionFactory() {
        return emf.unwrap(SessionFactory.class);
    }

    private Session getCurrentSession() {
        return getSessionFactory().getCurrentSession();
    }

    @Override
    public void addQuizQuestion(int quizId, int questionId) {
        QuizQuestion qq = new QuizQuestion();
        Quiz quiz = new Quiz();
        quiz.setQuizId(quizId);
        qq.setQuiz(quiz);

        Question question = new Question();
        question.setQuestionId(questionId);
        qq.setQuestion(question);

        getCurrentSession().save(qq);
    }

    @Override
    public List<QuizQuestion> getQuizQuestionsByQuizId(int quizId) {
        String hql = "FROM QuizQuestion qq WHERE qq.quiz.quizId = :quizId";
        return getCurrentSession().createQuery(hql, QuizQuestion.class)
                .setParameter("quizId", quizId)
                .list();
    }

    @Override
    public void updateUserChoice(int qqId, int userChoiceId) {
        QuizQuestion qq = getCurrentSession().get(QuizQuestion.class, qqId);
        if (qq != null) {
            Choice choice = new Choice();
            choice.setChoiceId(userChoiceId);
            qq.setUserChoice(choice);
            getCurrentSession().update(qq);
        }
    }
}