package com.pandy.blog.dao;

import com.pandy.blog.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Pandy
 */
@Repository
public interface RoleDao extends JpaRepository<Role, Integer> {

    Role getByRoleName(String roleName);

}
