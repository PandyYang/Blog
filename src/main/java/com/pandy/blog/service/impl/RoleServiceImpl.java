package com.pandy.blog.service.impl;

import com.pandy.blog.dao.RoleDao;
import com.pandy.blog.dao.UserRoleDao;
import com.pandy.blog.domain.Role;
import com.pandy.blog.dto.RoleListDTO;
import com.pandy.blog.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Pandy
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleDao roleDao;

    @Autowired
    UserRoleDao userRoleDao;

    @Override
    public List<RoleListDTO> getRoleList() {
        List<RoleListDTO> roleListDTOS = new ArrayList<>();

        final List<Role> all = roleDao.findAll();
        for (Role role : all) {
            RoleListDTO roleListDTO = new RoleListDTO();
            roleListDTO.setRoleName(role.getRoleName());
            roleListDTO.setTotal(userRoleDao.countAllByRoleId(role.getId()));
            roleListDTOS.add(roleListDTO);
        }
        return roleListDTOS;


//        final Map<String, List<Role>> roleMap = roleDao.findAll().stream().collect(Collectors.groupingBy(res -> res.getRoleName()));
//        for (Map.Entry<String, List<Role>> stringListEntry : roleMap.entrySet()) {
//            RoleListDTO roleListDTO = new RoleListDTO();
//            roleListDTO.setRoleName(stringListEntry.getKey());
//            roleListDTO.setTotal(stringListEntry.getValue().size());
//            roleListDTOS.add(roleListDTO);
//        }
//        return roleListDTOS;
    }
}
