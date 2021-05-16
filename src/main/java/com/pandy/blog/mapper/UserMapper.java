package com.pandy.blog.mapper;

import com.pandy.blog.config.IgnoreUnmappedMapperConfig;
import com.pandy.blog.dao.RoleDao;
import com.pandy.blog.domain.Role;
import com.pandy.blog.domain.User;
import com.pandy.blog.dto.UserDTO;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author Pandy
 */
@Mapper(componentModel = "spring",  config = IgnoreUnmappedMapperConfig.class)
public abstract class UserMapper {

    @Autowired
    private RoleDao roleDao;

    public abstract UserDTO entityToDTO(User user);

    @AfterMapping
    UserDTO convertRoleName(@MappingTarget User user) {
        final Role one = roleDao.getOne(user.getRoleId());
        UserDTO userDTO = new UserDTO();
        userDTO.setRoleName(one.getRoleName());
        return userDTO;
    }
}
