package com.pandy.blog.controller;

import com.pandy.blog.common.PageResult;
import com.pandy.blog.common.Result;
import com.pandy.blog.dto.ArticleDTO;
import com.pandy.blog.service.ArticleService;
import com.pandy.blog.vo.ArticleAddVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping("listArticle")
    public Result getArticleList(@RequestParam(name = "current", required = true) int current,
                                 @RequestParam(name = "size", required = true) int size,
                                 @RequestParam(name = "title", required = false) String title) {
        PageResult<ArticleDTO> articleDTOList = articleService.findAllByTitle(title, current, size);
        return Result.success().data("data", articleDTOList);
    }

    @GetMapping("getArticleById")
    public Result getById(@RequestParam int articleId) throws Exception {
        final ArticleDTO byId = articleService.getById(articleId);
        return Result.success().data("data", byId);
    }

    @DeleteMapping("deleteArticles")
    public Result deleteById(@RequestBody String str) {
        final List<Object> collect = Arrays.stream(Arrays.stream(str.split(",")).toArray()).collect(Collectors.toList());
        List<Integer> res = new ArrayList<>();
        for (Object o : collect) {
            res.add(Integer.parseInt(o.toString()));
        }
        articleService.deleteArticles(res);
        return Result.success();
    }
}
