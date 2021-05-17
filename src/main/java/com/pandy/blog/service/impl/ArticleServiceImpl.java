package com.pandy.blog.service.impl;

import com.pandy.blog.dao.ArticleDao;
import com.pandy.blog.dao.ArticleTagDao;
import com.pandy.blog.domain.Article;
import com.pandy.blog.domain.ArticleTag;
import com.pandy.blog.service.ArticleService;
import com.pandy.blog.vo.ArticleAddVo;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

/**
 * @author Pandy
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    ArticleDao articleDao;

    @Autowired
    ArticleTagDao articleTagDao;


    @Override
    @Transactional
    public void upsert(ArticleAddVo articleAddVo) {
        Date date = new Date();
        Article article = new Article(articleAddVo);
        if (articleAddVo.getId() == null) {
            article.setUpdateTime(date);
            article.setCreateTime(date);
        } else {
            article.setUpdateTime(date);
            articleTagDao.deleteAllByArticleId(articleAddVo.getId());
        }
        Article newArticle  = articleDao.save(article);
        if (articleAddVo.getTagIdList().size() > 0) {
            for (Integer tagId : articleAddVo.getTagIdList()) {
                ArticleTag articleTag = new ArticleTag();
                articleTag.setTagId(tagId);
                articleTag.setArticleId(newArticle.getId());
                articleTagDao.save(articleTag);
            }
        }
    }
}
