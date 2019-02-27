package com.cloudwing.checkstand.permission.service.impl;

import com.cloudwing.checkstand.office.mapper.OfficeMapper;
import com.cloudwing.checkstand.permission.entity.Role;
import com.cloudwing.checkstand.permission.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoleManager {

    @Autowired
    private RoleMapper roleMapper;

    @Cacheable
    public List<Role> listAllRole() {
        return roleMapper.selectAll();
    }

    public Map<Integer, Role> getIdMapRole() {
        Map<Integer, Role> idMapRole = new HashMap<Integer, Role>();
        List<Role> list = listAllRole();
        for (Role r : list) {
            idMapRole.put(r.getId(), r);
        }
        return idMapRole;
    }

    /**
     * 根据用户角色id获取用户角色名称描述
     * @param id
     * @return
     */
    public String getRoleDescById(Integer id) {
        Role role = getIdMapRole().get(id);
        if (null != role) {
            return role.getDescription();
        }
        return null;

    }

    public Map<String, Role> getCodeMapRole() {
        Map<String, Role> codeMapRole = new HashMap<String, Role>();
        List<Role> list = listAllRole();
        for (Role r : list) {
            codeMapRole.put(r.getCode(), r);
        }
        return codeMapRole;
    }

    /**
     * 根据用户角色代码获取用户角色名称描述
     * @param code
     * @return
     */
    public String getRoleDescByCode(String code) {
        Role role = getCodeMapRole().get(code);
        if (null != role) {
            return role.getDescription();
        }
        return null;
    }
}
