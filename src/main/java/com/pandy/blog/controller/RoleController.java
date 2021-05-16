package com.pandy.blog.controller;

import com.pandy.blog.common.Result;
import com.pandy.blog.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Pandy
 */
@RestController
@RequestMapping("role")
public class RoleController {

    @Autowired
    RoleService roleService;

    @GetMapping("/roleList")
    Result getRoleList() {
        return Result.success().data("userList", roleService.getRoleList());
    }
}
