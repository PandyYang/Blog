package com.pandy.blog.dao;

import com.pandy.blog.domain.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Pandy
 */
@Repository
public interface MenuDao extends JpaRepository<Menu, Integer> {
}
