package com.pandy.blog.service;

import com.pandy.blog.common.PageResult;
import com.pandy.blog.dto.ArticleDTO;
import com.pandy.blog.dto.ArticleHomeDTO;
import com.pandy.blog.dto.ArticleWebDTO;
import com.pandy.blog.vo.ArticleAddVo;

import java.util.List;

/**
 * @author Pandy
 */
public interface ArticleService {

     void upsert(ArticleAddVo articleAddVo);

     PageResult<ArticleDTO> findAllByTitle(String title, int current, int size);

     ArticleDTO getById(int id) throws Exception;

     void deleteArticles(List<Integer> ids);

     /**
      * 查询首页文章
      * @param current
      * @return
      */
     List<ArticleHomeDTO> listArticles(int current);

     /**
      * 查询web端的article
      * @param articleId
      * @return
      */
     ArticleWebDTO getArticleById(int articleId);

}
