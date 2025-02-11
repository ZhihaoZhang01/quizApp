package com.zhihao.quizapp.service;

import com.zhihao.quizapp.dao.ICategoryDAO;
import com.zhihao.quizapp.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Zhihao Zhang
 * @description TODO
 * @date 2025-02-01 8:53 PM
 */

@Service
public class CategoryService {
    @Autowired
    @Lazy
    private ICategoryDAO categoryDAO;


    @Transactional
    public List<Category> getAllCategories(){
        return categoryDAO.getAllCategories();
    }


}
