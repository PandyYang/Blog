package com.pandy.blog.mapper;

import com.pandy.blog.config.IgnoreUnmappedMapperConfig;
import com.pandy.blog.domain.Article;
import com.pandy.blog.domain.Role;
import com.pandy.blog.domain.User;
import com.pandy.blog.dto.ArticleDTO;
import com.pandy.blog.dto.UserDTO;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

/**
 * @author Pandy
 */
@Mapper(componentModel = "spring",  config = IgnoreUnmappedMapperConfig.class)
public abstract class ArticleMapper {

    public abstract ArticleDTO entityToDTO(Article article);

//    @AfterMapping
//    UserDTO convertRoleName(@MappingTarget User user) {
//        final Role one = roleDao.getOne(user.getRoleId());
//        UserDTO userDTO = new UserDTO();
//        userDTO.setRoleName(one.getRoleName());
//        return userDTO;
//    }

}
