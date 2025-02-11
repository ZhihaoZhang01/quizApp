package com.zhihao.quizapp.dao;

import com.zhihao.quizapp.model.Contact;

import java.util.List;

/**
 * @author Zhihao Zhang
 * @description TODO
 * @date 2025-02-08 11:23 PM
 */
public interface IContactDAO {
     List<Contact> getAllContacts();
     List<Contact> getContacts(int offset, int limit);
     Contact getContactById(int contact_id);
     void createNewContact(Contact contact);
}
