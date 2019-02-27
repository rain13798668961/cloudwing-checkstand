package com.cloudwing.checkstand.permission.mapper;

import com.cloudwing.checkstand.permission.entity.Role;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.BaseMapper;
import java.util.List;

import java.util.Set;

public interface RoleMapper extends BaseMapper<Role> {
//    int deleteByPrimaryKey(Integer id);
//
//    int insert(Role record);
//
//    int insertSelective(Role record);
//
//    Role selectByPrimaryKey(Integer id);
//
//    int updateByPrimaryKeySelective(Role record);
//
//    int updateByPrimaryKey(Role record);

    /**
     * 根据用户id查找用户角色 [role.code] 集合
     * @param userId
     * @return
     */
    Set<String> listCodeByUserId(@Param("userId")Integer userId);

    /**
     * 根据用户id查询Role数据
     * @param id
     * @return
     */
    List<Role> listByUserId(@Param("id") Integer id);
}