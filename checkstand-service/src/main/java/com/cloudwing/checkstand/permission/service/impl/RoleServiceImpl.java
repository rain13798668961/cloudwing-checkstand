package com.cloudwing.checkstand.permission.service.impl;

import com.cloudwing.checkstand.permission.entity.Role;
import com.cloudwing.checkstand.permission.mapper.RoleMapper;
import com.cloudwing.checkstand.permission.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Create by cjf on 2019/1/24.
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public String roleDescriptionByUserId(Integer id) {
        List<Role> roles = roleMapper.listByUserId(id);
        Integer rId = Integer.MAX_VALUE ;
        Map<Integer, String> map = new HashMap<>();
        for (Role role : roles) {
            map.put(role.getId(), role.getDescription());
        }

        Set<Integer> integers = map.keySet();
        for (Integer integer : integers) {
            if (rId > integer) {
                rId = integer;
            }
        }

        return map.get(rId);
    }

}
