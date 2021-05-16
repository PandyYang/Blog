package com.pandy.blog.controller;

import com.pandy.blog.common.Result;
import com.pandy.blog.dto.UserDTO;
import com.pandy.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Pandy
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/getUserList")
    Result getUserList(@RequestParam(name = "roleName", required = false) String roleName,
                       @RequestParam(name = "nickname", required = false) String nickName,
                       @RequestParam(name = "current", required = true) int current,
                       @RequestParam(name = "size", required = true) int size) {
        final List<UserDTO> userList = userService.getUserList(roleName, nickName, current, size);
        return Result.success().data("data", userList).data("total", userList.size());
    }

}
