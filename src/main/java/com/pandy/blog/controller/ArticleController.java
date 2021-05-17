package com.pandy.blog.controller;

import com.pandy.blog.common.Result;
import com.pandy.blog.service.ArticleService;
import com.pandy.blog.vo.ArticleAddVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Pandy
 */

@RestController
@RequestMapping("article")
public class ArticleController {

    @Autowired
    ArticleService articleService;

    @PostMapping("saveOrUpdateArticle")
    public Result upsertArticle(@RequestBody ArticleAddVo articleAddVo) {
        articleService.upsert(articleAddVo);
        return Result.success().code("200");
    }
}
