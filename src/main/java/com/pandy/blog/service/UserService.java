package com.pandy.blog.service;

import com.pandy.blog.common.PageResult;
import com.pandy.blog.domain.User;
import com.pandy.blog.dto.UserDTO;
import com.pandy.blog.vo.UserQueryVo;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author Pandy
 */
public interface UserService {

    User getUserByName(String name);

    List<UserDTO> getUserByCondition(UserQueryVo userQueryVo);

    PageResult<UserDTO> getUserList(String roleName, String nickName, int current, int size);
}
