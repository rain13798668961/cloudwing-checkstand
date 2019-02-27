package com.cloudwing.checkstand.permission.service;

/**
 * Create by cjf on 2019/1/24.
 */
public interface RoleService {
    /**
     * 根据用户id查询Role数据权限最大的Description
     * @param id
     * @return
     */
    String roleDescriptionByUserId(Integer id);
}
