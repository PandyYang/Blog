package com.pandy.blog.dao;

import com.pandy.blog.domain.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Pandy
 */
@Repository
public interface CategoryDao extends JpaRepository<Category, Integer> {
    Page<Category> findAllByCategoryContaining(String name, Pageable pageable);
}
