package com.pandy.blog.service;

import com.pandy.blog.domain.Role;
import com.pandy.blog.dto.RoleListDTO;

import java.util.List;

/**
 * @author Pandy
 */
public interface RoleService {

    List<RoleListDTO> getRoleList();
}
