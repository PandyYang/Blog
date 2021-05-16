package com.pandy.blog.controller;

import com.pandy.blog.common.Result;
import com.pandy.blog.domain.Category;
import com.pandy.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Pandy
 */
@RestController
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("getCategoryList")
    Result getCategoryList() {
        final List<Category> categoryList = categoryService.getCategoryList();
        return Result.success().data("data", categoryList);
    }
}
