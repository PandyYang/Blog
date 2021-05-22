package com.pandy.blog.service;

import com.pandy.blog.common.PageResult;
import com.pandy.blog.domain.Category;
import com.pandy.blog.dto.CategoryDTO;
import com.pandy.blog.vo.CategoryAddDTO;

import java.util.List;

/**
 * @author Pandy
 */
public interface CategoryService {

    List<Category> getCategoryList();

    void upsert(CategoryAddDTO categoryAddDTO);

    PageResult<CategoryDTO> listCategory(String categoryName, int current,  int size);

}
