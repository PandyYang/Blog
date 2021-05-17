package com.pandy.blog.service;

import com.pandy.blog.common.PageResult;
import com.pandy.blog.domain.Article;
import com.pandy.blog.dto.ArticleDTO;
import com.pandy.blog.vo.ArticleAddVo;
import org.springframework.data.domain.PageRequest;

/**
 * @author Pandy
 */
public interface ArticleService {

     void upsert(ArticleAddVo articleAddVo);

     PageResult<ArticleDTO> findAllByTitle(String title, int current, int size);
}
