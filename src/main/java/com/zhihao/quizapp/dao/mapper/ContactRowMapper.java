package com.zhihao.quizapp.dao.mapper;

import com.zhihao.quizapp.model.Contact;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Zhihao Zhang
 * @description TODO
 * @date 2025-02-01 10:42 PM
 */
@Component
public class ContactRowMapper implements RowMapper<Contact> {

    @Override
    public Contact mapRow(ResultSet rs, int rowNum) throws SQLException {
        Contact contact = new Contact();
        contact.setContactId(rs.getInt("contact_id"));
        contact.setSubject(rs.getString("subject"));
        contact.setEmail(rs.getString("email"));
        contact.setMessage(rs.getString("message"));
        contact.setTime(rs.getTime("time"));
        return contact;
    }
}
