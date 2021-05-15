package com.pandy.blog.dao;

import com.pandy.blog.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Pandy
 */
@Repository
public interface UserDao extends JpaRepository<User, Integer> {

    User getByUsername(String name);

}
