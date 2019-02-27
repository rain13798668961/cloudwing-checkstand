package com.cloudwing.checkstand.permission.service.impl;

import com.cloudwing.checkstand.common.utils.UserHelper;
import com.cloudwing.checkstand.permission.entity.Permission;
import com.cloudwing.checkstand.permission.mapper.PermissionMapper;
import com.cloudwing.checkstand.permission.service.PermissionService;
import com.cloudwing.checkstand.permission.vo.Menu;
import com.cloudwing.checkstand.permission.vo.MenuItem;
import com.cloudwing.checkstand.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.PrintWriter;
import java.util.*;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public List<Menu> listMenusByUserId(User user) {
        List<Permission> perms;
        if (UserHelper.hasSuperAdminRoles(user.getRoles())) {
            List<Permission> l = permissionMapper.selectAll();
            perms = new ArrayList<Permission>();
            for (Permission p : l) {
                if (p.getIsMenu()) {
                    perms.add(p);
                }
            }
        } else {
            perms = permissionMapper.listMenusByUserId(user.getId());
        }
        return permsToMenus(perms);
    }

    private List<Menu> permsToMenus(List<Permission> permissions) {
        Map<Integer, Permission> idMapPerm = new LinkedHashMap<Integer, Permission>();
        for(Permission perm : permissions){
            idMapPerm.put(perm.getId(), perm);
        }
        idMapPerm.remove(1);
        List<Menu> menus = new ArrayList<Menu>();
        for (Map.Entry<Integer, Permission> entry : idMapPerm.entrySet()) {
            if (entry.getValue().getpId().equals(1)) {
                Menu menu = new Menu();
                menu.setId(entry.getValue().getId());
                menu.setName(entry.getValue().getName());
                menu.setMenuItems(getChild(menu.getId(),permissions));
                menus.add(menu);
            }
        }


        return menus;

    }

    private List<MenuItem> getChild(Integer id, List<Permission> perms) {
        List<MenuItem> menuItems = new ArrayList<MenuItem>();

        for(Permission perm : perms) {
            if (perm.getpId() != null && perm.getpId().equals(id)) {
                MenuItem item = new MenuItem();
                item.setMenuId(id);
                item.setName(perm.getName());
                item.setUrl(perm.getUrl());
                menuItems.add(item);
            }
        }
        return menuItems;
    }
}
