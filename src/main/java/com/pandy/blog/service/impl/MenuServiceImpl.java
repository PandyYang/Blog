package com.pandy.blog.service.impl;

import com.pandy.blog.dao.MenuDao;
import com.pandy.blog.dao.RoleDao;
import com.pandy.blog.domain.Menu;
import com.pandy.blog.domain.Role;
import com.pandy.blog.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        Menu menuDTO = null;
        for (Menu menu : menuList) {
            if (menu.getParentId() == null || menu.getParentId() == 0) {
                // 父级菜单
                findChildren(menu, menuList);
                menuDTOList.add(menu);
            }
        }
        return menuDTOList;
    }

    private void findChildren(Menu menu, List<Menu> menuList) {
        for (Menu child : menuList) {
            if (child.getParentId() == menu.getId()) {
                menu.getChildren().add(child);
            }
        }
        if (menu.getChildren().size() == 0) {
            return;
        }
        for (Menu menu1 : menu.getChildren()) {
            findChildren(menu1, menuList);
        }
    }
}
