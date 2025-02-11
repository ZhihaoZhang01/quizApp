package com.zhihao.quizapp.service;


import com.zhihao.quizapp.dao.IContactDAO;
import com.zhihao.quizapp.model.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Zhihao Zhang
 * @description TODO
 * @date 2025-02-01 11:02 PM
 */


@Service
public class ContactService {

    @Autowired
    @Lazy
    private IContactDAO contactDAO;


    @Transactional
    public List<Contact> getAllContact() {
        return contactDAO.getAllContacts();
    }

    @Transactional
    public void createNewContact(Contact contact){
        contactDAO.createNewContact(contact);
    }


}
