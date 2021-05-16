package com.pandy.blog.dao;

import com.pandy.blog.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Pandy
 */
@Repository
public interface TagDao extends JpaRepository<Tag, Integer> {
}
