package com.zhihao.quizapp.dao.hibernate;

import com.zhihao.quizapp.dao.IContactDAO;
import com.zhihao.quizapp.model.Contact;
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
 * @date 2025-02-08 11:26 PM
 */

@Repository("hibernateContactDAO")
@Primary
public class ContactDaoHibernateImpl implements IContactDAO {

    @Autowired
    private EntityManagerFactory emf;

    private SessionFactory getSessionFactory() {
        return emf.unwrap(SessionFactory.class);
    }

    private Session getCurrentSession() {
        return getSessionFactory().getCurrentSession();
    }

    @Override
    public List<Contact> getAllContacts(){
        return getCurrentSession().createQuery("FROM Contact", Contact.class).list();
    }

    @Override
    public List<Contact> getContacts(int offset, int limit) {
        String hql = "FROM Contact ORDER BY time DESC";
        return getCurrentSession().createQuery(hql, Contact.class)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .list();
    }

    @Override
    public Contact getContactById(int contactId) {
        return getCurrentSession().get(Contact.class, contactId);
    }

    @Override
    public void createNewContact(Contact contact) {
        getCurrentSession().save(contact);
    }
}
