package com.cloudwing.checkstand.permission.mapper;

import com.cloudwing.checkstand.common.mapper.MyMapper;
import com.cloudwing.checkstand.permission.entity.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;
import java.util.Set;

public interface PermissionMapper extends MyMapper<Permission> {
//    int deleteByPrimaryKey(Integer id);
//
//    int insert(Permission record);
//
//    int insertSelective(Permission record);
//
//    Permission selectByPrimaryKey(Integer id);
//
//    int updateByPrimaryKeySelective(Permission record);
//
//    int updateByPrimaryKey(Permission record);
//

    /**
     *
     * @param userId
     * @return
     */
    List<Permission> listMenusByUserId(@Param("userId") Integer userId);

    /**
     * 根据用户id查找用户权限 [permission.url] 集合
     * @param userId
     * @return
     */
    Set<String> listUrlByUserId(@Param("userId")Integer userId);

    /**
     * 获取所有的用户权限 [permission.url] 集合
     * @return
     */
    Set<String> listAllUrl();

}