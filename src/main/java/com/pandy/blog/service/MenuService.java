package com.pandy.blog.service;

import com.pandy.blog.domain.Menu;

import java.util.List;

/**
 * @author Pandy
 */
public interface MenuService {

    /**
     * 通过角色名查找对应的菜单
     * @param roleName
     * @return
     */
    List<Menu> listMenusByUserRole(String roleName);
}
