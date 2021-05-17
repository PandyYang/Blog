package com.pandy.blog.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.pandy.blog.common.PageResult;
import com.pandy.blog.dao.ArticleDao;
import com.pandy.blog.dao.ArticleTagDao;
import com.pandy.blog.dao.CategoryDao;
import com.pandy.blog.dao.TagDao;
import com.pandy.blog.domain.Article;
import com.pandy.blog.domain.ArticleTag;
import com.pandy.blog.domain.Category;
import com.pandy.blog.domain.Tag;
import com.pandy.blog.dto.ArticleDTO;
import com.pandy.blog.mapper.ArticleMapper;
import com.pandy.blog.service.ArticleService;
import com.pandy.blog.vo.ArticleAddVo;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Pandy
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    ArticleDao articleDao;

    @Autowired
    ArticleTagDao articleTagDao;

    @Autowired
    ArticleMapper articleMapper;

    @Autowired
    CategoryDao categoryDao;

    @Autowired
    TagDao tagDao;


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

    @Override
    public PageResult<ArticleDTO> findAllByTitle(String title, int current, int size) {
        Page<Article> allByTitleContaining = null;
        if (!StringUtils.isEmpty(title)) {
            allByTitleContaining = articleDao.findAllByTitleContaining(title, PageRequest.of(current - 1, size));
        } else {
            allByTitleContaining = articleDao.findAll(PageRequest.of(current - 1, size));
        }
        List<ArticleDTO> articleDTOS = new ArrayList<>();
        for (Article article : allByTitleContaining.getContent()) {
            final ArticleDTO articleDTO = articleMapper.entityToDTO(article);
            final Category category = categoryDao.findById(article.getCategoryId()).get();
            articleDTO.setCategoryName(category.getCategory());
            final List<ArticleTag> allByArticleId = articleTagDao.findAllByArticleId(article.getId());
            List<String> tagNames = new ArrayList<>();
            for (ArticleTag articleTag : allByArticleId) {
                final Tag one = tagDao.getOne(articleTag.getTagId());
                final String tag = one.getTag();
                tagNames.add(tag);
            }
            articleDTO.setTagsName(tagNames);
            articleDTOS.add(articleDTO);
        }
        return  new PageResult(articleDTOS, (int) allByTitleContaining.getTotalElements());
    }
}
