package com.pandy.blog.service.impl;

import com.pandy.blog.common.PageResult;
import com.pandy.blog.dao.CategoryDao;
import com.pandy.blog.domain.Category;
import com.pandy.blog.dto.CategoryDTO;
import com.pandy.blog.service.CategoryService;
import com.pandy.blog.vo.CategoryAddDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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

    @Override
    public void upsert(CategoryAddDTO categoryAddDTO) {
        Date now = new Date();
        Category category = new Category();
        if (categoryAddDTO.getCategoryId() == null) {
            category.setCreateTime(now);
            category.setUpdateTime(now);
            category.setCategory(categoryAddDTO.getCategoryName());
        }else {
            Category category1 = categoryDao.findById(categoryAddDTO.getCategoryId()).get();
            category.setDeleteTime(category1.getDeleteTime());
            category.setCreateTime(category1.getCreateTime());
            category.setUpdateTime(category1.getUpdateTime());
            category.setCategory(categoryAddDTO.getCategoryName());
            category.setId(categoryAddDTO.getCategoryId());
        }
        categoryDao.save(category);
    }

    @Override
    public PageResult<CategoryDTO> listCategory(String categoryName, int current, int size) {
        Page<Category> categoryDTOS = null;
        if (StringUtils.isEmpty(categoryName)) {
            categoryDTOS = categoryDao.findAll(PageRequest.of(current - 1, size));
        } else {
            categoryDTOS = categoryDao.findAllByCategoryContaining(categoryName, PageRequest.of(current - 1, size));
        }
        final List<Category> content = categoryDTOS.getContent();
        List<CategoryDTO> categoryDTOList = new ArrayList<>();
        for (Category category : content) {
            CategoryDTO categoryDTO = new CategoryDTO();
            categoryDTO.setCategoryId(category.getId());
            categoryDTO.setCategoryName(category.getCategory());
            categoryDTO.setUpdateTime(category.getUpdateTime());
            category.setCreateTime(category.getCreateTime());
            categoryDTOList.add(categoryDTO);
        }
        PageResult<CategoryDTO> categoryDTOPageResult = new PageResult<>();
        categoryDTOPageResult.setItems(categoryDTOList);
        categoryDTOPageResult.setTotal((int) categoryDTOS.getTotalElements());
        return categoryDTOPageResult;
    }
}
