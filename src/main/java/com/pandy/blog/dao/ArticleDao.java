package com.pandy.blog.dao;

import com.pandy.blog.domain.Article;
import com.pandy.blog.dto.ArticleHomeDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Pandy
 */
@Repository
public interface ArticleDao extends JpaRepository<Article, Integer> {

    Page<Article> findAllByTitleContaining(String title, Pageable pageable);

    List<Article> findAllByCategoryId(int categoryId);
}
