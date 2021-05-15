package com.pandy.blog.dao;

import com.pandy.blog.domain.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Pandy
 */
@Repository
public interface MenuDao extends JpaRepository<Menu, Integer> {

    //@Query(value = "select * from  Menu where parent_id = :parentId", nativeQuery = true)
    List<Menu> getAllByParentId(Integer parentId);
}
