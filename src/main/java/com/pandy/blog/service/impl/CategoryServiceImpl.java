package com.pandy.blog.service.impl;

import com.pandy.blog.dao.CategoryDao;
import com.pandy.blog.domain.Category;
import com.pandy.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Pandy
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    @Override
    public List<Category> getCategoryList() {
        return categoryDao.findAll();
    }
}
