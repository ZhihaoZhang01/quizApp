package com.zhihao.quizapp.dao.mapper;

import com.zhihao.quizapp.model.Category;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Zhihao Zhang
 * @description TODO
 * @date 2025-01-31 10:30 PM
 */

@Component
public class CategoryRowMapper implements RowMapper<Category> {
    @Override
    public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
        Category category = new Category();
        category.setName(rs.getString("name"));
        category.setCategoryId(rs.getInt("category_id"));
        return category;
    }
}
