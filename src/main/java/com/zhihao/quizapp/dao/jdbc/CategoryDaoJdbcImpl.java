package com.zhihao.quizapp.dao.jdbc;

import com.zhihao.quizapp.dao.ICategoryDAO;
import com.zhihao.quizapp.dao.mapper.CategoryRowMapper;
import com.zhihao.quizapp.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Zhihao Zhang
 * @description TODO
 * @date 2025-01-31 10:30 PM
 */

@Repository("jdbcCategoryDAO")
public class CategoryDaoJdbcImpl implements ICategoryDAO {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    CategoryRowMapper categoryRowMapper;

    @Override
    public List<Category> getAllCategories(){
        String query = "SELECT * FROM category";
        return jdbcTemplate.query(query, categoryRowMapper);
    }

    @Override
    public Category getCategoryById(int categoryId) {
        String query = "SELECT * FROM Category WHERE category_id = ?";
        return jdbcTemplate.queryForObject(query, categoryRowMapper, categoryId);
    }
}
