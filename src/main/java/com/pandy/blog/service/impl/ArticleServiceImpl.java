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
import com.pandy.blog.dto.*;
import com.pandy.blog.mapper.ArticleMapper;
import com.pandy.blog.service.ArticleService;
import com.pandy.blog.vo.ArticleAddVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
            if (article.getCategoryId() != null) {
                final Category category = categoryDao.findById(article.getCategoryId()).get();
                articleDTO.setCategoryName(category.getCategory());
            }
            final List<ArticleTag> allByArticleId = articleTagDao.findAllByArticleId(article.getId());
            if (!allByArticleId.isEmpty()) {
                List<String> tagNames = new ArrayList<>();
                for (ArticleTag articleTag : allByArticleId) {
                    final Tag one = tagDao.getOne(articleTag.getTagId());
                    final String tag = one.getTag();
                    tagNames.add(tag);
                }
                articleDTO.setTagsName(tagNames);
            }
            articleDTOS.add(articleDTO);
        }
        return  new PageResult(articleDTOS, (int) allByTitleContaining.getTotalElements());
    }

    @Override
    public ArticleDTO getById(int id) throws Exception {
        final Article article = articleDao.findById(id).orElseThrow(Exception::new);
        final ArticleDTO articleDTO = articleMapper.entityToDTO(article);
        if (article.getCategoryId() != null) {
            final Category category = categoryDao.findById(article.getCategoryId()).get();
            articleDTO.setCategoryName(category.getCategory());
            articleDTO.setCategoryId(category.getId());
        }

        List<Tag> tags = new ArrayList<>();
        List<Integer> taIdList = new ArrayList<>();
        final List<ArticleTag> allByArticleId = articleTagDao.findAllByArticleId(article.getId());
        if ( allByArticleId!= null) {
            for (ArticleTag articleTag : allByArticleId) {
                final Integer tagId = articleTag.getTagId();
                final Tag one = tagDao.findById(tagId).get();
                tags.add(one);
                taIdList.add(one.getId());
            }
            articleDTO.setTagIdList(taIdList);
            articleDTO.setTagList(tags);
        }
        articleDTO.setTagsName(tags.stream().map(Tag::getTag).collect(Collectors.toList()));
        return articleDTO;
    }

    @Override
    public void deleteArticles(List<Integer> param) {
        for (Integer integer : param) {
            articleDao.deleteById(integer);
        }
    }

    @Override
    public List<ArticleHomeDTO> listArticles(int current) {
        final Page<Article> all = articleDao.findAll(PageRequest.of(current - 1, 10));
        List<ArticleHomeDTO> articleHomeDTOS = new ArrayList<>();
        for (Article article : all) {
            final Category category = categoryDao.findById(article.getCategoryId()).get();
            final List<ArticleTag> allByArticleId = articleTagDao.findAllByArticleId(article.getId());
            List<TagDTO> tagDTOList = new ArrayList<>();
            for (ArticleTag articleTag : allByArticleId) {
                final Tag tag = tagDao.findById(articleTag.getTagId()).get();
                tagDTOList.add(new TagDTO(tag.getId(), tag.getTag(), tag.getCreateTime(), tag.getUpdateTime()));
            }
            articleHomeDTOS.add(new ArticleHomeDTO(article.getId(), article.getPicture(), article.getTitle(),
                    article.getContent(), article.getCreateTime(), article.isTop(), category.getId(), category.getCategory(),
                    tagDTOList));
        }
        return articleHomeDTOS;
    }

    @Override
    public ArticleWebDTO getArticleById(int articleId) {

        // 更新文章浏览量 在redis更新
        ArticleWebDTO articleWebDTO = new ArticleWebDTO();
        // 查询上一篇文章
        if (articleId == 1) {
            ArticlePaginationDTO articlePaginationDTO2 = new ArticlePaginationDTO();
            final Article one = articleDao.getOne(articleId + 1);
            articlePaginationDTO2.setId(one.getId());
            articlePaginationDTO2.setArticleCover(one.getPicture());
            articlePaginationDTO2.setArticleTitle(one.getTitle());
            articleWebDTO.setNextArticle(articlePaginationDTO2);

            articleWebDTO.setLastArticle(articlePaginationDTO2);
        } else {
            ArticlePaginationDTO articlePaginationDTO = new ArticlePaginationDTO();
            final Article one = articleDao.getOne(articleId - 1);
            articlePaginationDTO.setId(one.getId());
            articlePaginationDTO.setArticleCover(one.getPicture());
            articlePaginationDTO.setArticleTitle(one.getTitle());
            articleWebDTO.setLastArticle(articlePaginationDTO);
        }
        // 查询下一篇文章
        ArticlePaginationDTO articlePaginationDTO2 = new ArticlePaginationDTO();
        if (articleDao.findById(articleId + 1).isPresent()) {
            final Article one = articleDao.getOne(articleId + 1);
            articlePaginationDTO2.setId(one.getId());
            articlePaginationDTO2.setArticleCover(one.getPicture());
            articlePaginationDTO2.setArticleTitle(one.getTitle());
            articleWebDTO.setNextArticle(articlePaginationDTO2);
        } else {
            final Article one = articleDao.getOne(articleId);
            articlePaginationDTO2.setId(one.getId());
            articlePaginationDTO2.setArticleCover(one.getPicture());
            articlePaginationDTO2.setArticleTitle(one.getTitle());
            articleWebDTO.setNextArticle(articlePaginationDTO2);
        }
        // 查询相关推荐文章
        // 封装点赞和浏览量
        Article article = new Article();
        final Article one1 = articleDao.getOne(articleId);
        articleWebDTO.setId(one1.getId());
        articleWebDTO.setArticleCover(one1.getPicture());
        articleWebDTO.setArticleTitle(one1.getTitle());
        articleWebDTO.setArticleContent(one1.getContent());
        articleWebDTO.setCreateTime(one1.getCreateTime());
        articleWebDTO.setUpdateTime(one1.getUpdateTime());
        if (article.getCategoryId() != null) {
            final Category category = categoryDao.findById(article.getCategoryId()).get();
            articleWebDTO.setCategoryId(category.getId());
            articleWebDTO.setCategoryName(category.getCategory());
        }
        final List<ArticleTag> allByArticleId = articleTagDao.findAllByArticleId(articleId);
        if (!allByArticleId.isEmpty()) {
            List<TagDTO> tagDTOList = new ArrayList<>();
            for (ArticleTag articleTag : allByArticleId) {
                final Tag tag = tagDao.findById(articleTag.getTagId()).get();
                tagDTOList.add(new TagDTO(tag.getId(), tag.getTag(), tag.getCreateTime(), tag.getUpdateTime()));
            }
            articleWebDTO.setTagDTOList(tagDTOList);
        }
        return articleWebDTO;
    }
}
