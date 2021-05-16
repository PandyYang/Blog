package com.pandy.blog.service;

import com.pandy.blog.domain.User;
import com.pandy.blog.dto.UserDTO;
import com.pandy.blog.vo.UserQueryVo;

import java.util.List;

/**
 * @author Pandy
 */
public interface UserService {

    User getUserByName(String name);

    List<UserDTO> getUserByCondition(UserQueryVo userQueryVo);

    List<UserDTO> getUserList(String roleName, String nickName, int current, int size);
}
