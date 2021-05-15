package com.pandy.blog.controller;

import com.pandy.blog.common.Result;
import com.pandy.blog.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Pandy
 */
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

//    @GetMapping("/menus")
//    public Result listMenuByUserRole() {
//
//    }
}
