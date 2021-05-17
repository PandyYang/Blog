package com.pandy.blog.dao;

import com.pandy.blog.domain.ArticleTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Pandy
 */
@Repository
public interface ArticleTagDao extends JpaRepository<ArticleTag, Integer> {

    void deleteAllByArticleId(int id);
}
