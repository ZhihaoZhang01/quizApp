package com.zhihao.quizapp.dao.jdbc;

import com.zhihao.quizapp.dao.IContactDAO;
import com.zhihao.quizapp.dao.mapper.ContactRowMapper;
import com.zhihao.quizapp.model.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Zhihao Zhang
 * @description TODO
 * @date 2025-02-01 10:42 PM
 */

@Repository("jdbcContactDAO")
public class ContactDaoJdbcImpl implements IContactDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private ContactRowMapper contactRowMapper;

    @Override
    public List<Contact> getAllContacts(){
        String query = "SELECT * FROM contact";
        return jdbcTemplate.query(query,contactRowMapper);
    }
    @Override
    public List<Contact> getContacts(int offset, int limit) {
        String query = "SELECT * FROM contact LIMIT ? OFFSET ?";
        return jdbcTemplate.query(query, contactRowMapper, limit, offset);
    }
    @Override
    public Contact getContactById(int contact_id) {
        String query = "SELECT * FROM contact WHERE contact_id = ?";
        return jdbcTemplate.queryForObject(query, contactRowMapper, contact_id);
    }
    @Override
    public void createNewContact(Contact contact){
        String query = "INSERT INTO contact(contact_id, subject, message, email) VALUES (?,?,?,?)";
        jdbcTemplate.update(query,contact.getContactId(),contact.getSubject(),contact.getMessage(),contact.getEmail());
    }

}
