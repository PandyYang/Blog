package com.pandy.blog.dao;

import com.pandy.blog.domain.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Pandy
 */
@Repository
public interface RoleDao extends JpaRepository<Role, Integer> {

    Role getByRoleName(String roleName);

    Page<Role> getAllByRoleName(String roleName, Pageable pageable);
}
