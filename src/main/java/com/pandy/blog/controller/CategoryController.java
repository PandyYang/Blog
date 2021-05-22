package com.pandy.blog.controller;

import com.pandy.blog.common.PageResult;
import com.pandy.blog.common.Result;
import com.pandy.blog.domain.Category;
import com.pandy.blog.dto.ArticleDTO;
import com.pandy.blog.dto.CategoryDTO;
import com.pandy.blog.service.CategoryService;
import com.pandy.blog.vo.CategoryAddDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("addOrEditCategory")
    Result upsert(@RequestBody CategoryAddDTO data) {
        categoryService.upsert(data);
        return Result.success();
    }

    // 管理端列表
    @GetMapping("listCategory")
    public Result getArticleList(@RequestParam(name = "current", required = true) int current,
                                 @RequestParam(name = "size", required = true) int size,
                                 @RequestParam(name = "categoryName", required = false) String categoryName) {
        final PageResult<CategoryDTO> categoryDTOPageResult = categoryService.listCategory(categoryName, current, size);
        return Result.success().data("data", categoryDTOPageResult);
    }

    // 博客端列表
    @GetMapping("list")
    public Result listCategories() {
        return Result.success().data("data", categoryService.list());
    }

}
