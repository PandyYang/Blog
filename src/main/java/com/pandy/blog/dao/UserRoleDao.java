package com.pandy.blog.dao;

import com.pandy.blog.domain.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Pandy
 */
@Repository
public interface UserRoleDao extends JpaRepository<UserRole, Integer> {
}
