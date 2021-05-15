package com.pandy.blog.controller;

import com.pandy.blog.common.Result;
import com.pandy.blog.domain.Menu;
import com.pandy.blog.service.MenuService;
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
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping("/getMenuList")
    public Result listMenusByUserRole(@RequestParam String roleName) {
        List<Menu> menuDTOList = menuService.listMenusByUserRole("admin");
        return Result.success().data("menuList", menuDTOList);
    }
}
