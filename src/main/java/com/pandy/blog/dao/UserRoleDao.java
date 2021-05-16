package com.pandy.blog.dao;

import com.pandy.blog.domain.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Pandy
 */
@Repository
public interface UserRoleDao extends JpaRepository<UserRole, Integer> {

    List<UserRole> getAllByUserId(int userId);

    List<UserRole> getAllByRoleId(int roleId);

    UserRole getAllByRoleIdAndUserId(int roleId, int userId);
}
