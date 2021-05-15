package com.pandy.blog.controller;

import com.pandy.blog.common.Result;
import com.pandy.blog.domain.Role;
import com.pandy.blog.domain.User;
import com.pandy.blog.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Pandy
 */
@RestController
@Api(tags = "测试模块")
public class Test {

    @Autowired
    private UserService userService;

    @GetMapping("/test")
    @ApiOperation("测试接口")
    public Result test() {
        int i = 1/0;
        return new Result().code("200").message("好了").data("data", "haha");
    }

    @GetMapping("/test2")
    public Result test2() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AbstractAuthenticationToken test = (AbstractAuthenticationToken) authentication;
        Object details = test.getDetails();
        System.out.println("details = " + details);
        UsernamePasswordAuthenticationToken test2 = (UsernamePasswordAuthenticationToken) authentication;
        final Object principal = test2.getPrincipal();
        System.out.println("principal = " + principal);
        return Result.success();
    }

    @GetMapping("/test3")
    public Result test3() {
        final User pandy = userService.getUserByName("pandy");
        final List<Role> roles = pandy.getRoles();
        for (Role role : roles) {
            System.out.println(role);
        }
        return new Result().data("roles" , roles);
    }

}
