package com.pandy.blog.dao;

import com.pandy.blog.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Pandy
 */
@Repository
public interface ArticleDao extends JpaRepository<Article, Integer> {
}
