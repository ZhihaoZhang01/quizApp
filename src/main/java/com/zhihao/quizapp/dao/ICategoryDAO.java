package com.zhihao.quizapp.dao;

import com.zhihao.quizapp.model.Category;

import java.util.List;

/**
 * @author Zhihao Zhang
 * @description TODO
 * @date 2025-02-08 10:49 PM
 */
public interface ICategoryDAO {
    List<Category> getAllCategories();
    Category getCategoryById(int categoryId);
}
