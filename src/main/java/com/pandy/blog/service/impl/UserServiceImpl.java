package com.pandy.blog.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.pandy.blog.dao.RoleDao;
import com.pandy.blog.dao.UserDao;
import com.pandy.blog.domain.Role;
import com.pandy.blog.domain.User;
import com.pandy.blog.dto.UserDTO;
import com.pandy.blog.mapper.UserMapper;
import com.pandy.blog.service.UserService;
import com.pandy.blog.vo.UserQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Pandy
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserByName(String name) {
        return userDao.getByUsername(name);
    }

    @Override
    public List<UserDTO> getUserByCondition(UserQueryVo userQueryVo) {

//        // 同时进行查询 首先查询角色下的所有用户
//        if (userQueryVo.getRoleName() != null && userQueryVo.getNickName() != null) {
//            roleDao.getAllByRoleName()
//        }
        return null;
    }

    @Override
    public List<UserDTO> getUserList(String roleName, String nickName, int current, int size) {
        List<UserDTO> userDTOS = new ArrayList<>();
        if (StringUtils.isEmpty(roleName) && StringUtils.isEmpty(nickName)) {
            Page<User> pageUsers = userDao.findAll(PageRequest.of(current-1, size));
            List<User> users = pageUsers.getContent();
            for (User user : users) {
                final UserDTO userDTO = userMapper.entityToDTO(user);
                final Role one = roleDao.getOne(user.getRoleId());
                userDTO.setRoleName(one.getRoleName());
                userDTOS.add(userDTO);
            }
        }
        return userDTOS;
    }
}
