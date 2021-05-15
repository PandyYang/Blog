package com.pandy.blog.service.impl;

import com.pandy.blog.dao.MenuDao;
import com.pandy.blog.dao.RoleDao;
import com.pandy.blog.domain.Menu;
import com.pandy.blog.domain.Role;
import com.pandy.blog.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Pandy
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private MenuDao menuDao;

    @Override
    public List<Menu> listMenusByUserRole(String roleName) {
        Role role = roleDao.getByRoleName(roleName);
        List<Menu> menuList = role.getMenus();
        List<Menu> menuDTOList = new ArrayList<>();
        int[] used = new int[menuList.size()];
        int length = menuList.size();
        Arrays.fill(used, 0);
        for (int i = 0; i < length; i++) {
            Menu menu = menuList.get(i);
            used[i] = 1;
            if (menu.getParentId() == null || menu.getParentId() == 0) {
                // 父级菜单
                findChildren(menu, menuList, used);
                menuDTOList.add(menu);
            }
        }
        return menuDTOList;
    }

    private void findChildren(Menu menu, List<Menu> menuList, int[] used) {
        for (int i = 0; i < menuList.size(); i++) {
            if (used[i] == 1) {
                continue;
            }
            if (menuList.get(i).getParentId() != null) {
                if (menuList.get(i).getParentId().equals(menu.getId())) {
                    used[i] = 1;
                    menu.getChildren().add(menuList.get(i));
                }
            }
        }
        if (menu.getChildren().size() == 0) {
            return;
        }
        for (Menu menu1 : menu.getChildren()) {
            findChildren(menu1, menuList, used);
        }
    }
}
