package com.pandy.blog.service;

import com.pandy.blog.domain.Article;
import com.pandy.blog.vo.ArticleAddVo;

/**
 * @author Pandy
 */
public interface ArticleService {

     void upsert(ArticleAddVo articleAddVo);
}
