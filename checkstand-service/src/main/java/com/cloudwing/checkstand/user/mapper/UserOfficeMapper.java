package com.cloudwing.checkstand.user.mapper;

import com.cloudwing.checkstand.user.entity.UserOffice;
import org.apache.ibatis.annotations.Param;

public interface UserOfficeMapper {
    int insert(UserOffice record);

    int insertSelective(UserOffice record);

    /**
     * 根据userId查找对应的oid
     * @param userId
     * @return
     */
    Integer[] selectOidByUid(@Param("userId") Integer userId);

    /**
     * 根据oids查找对应的uid
     * @param oids
     * @return
     */
    Integer[] selectUidByOid(@Param("oids") Integer[] oids);

    /**
     * 根据userId And office增加一条数据
     * @param userId
     * @param office
     * @return
     */
    Integer saveByUserIdAndOfficeId(@Param("userId") Integer userId, @Param("officeId") Integer office);

    /**
     * 根据userId And office删除一条数据
     * @param userId
     * @param office
     * @return
     */
    Integer removeByUserIdAndOfficeId(@Param("userId") Integer userId, @Param("officeId") Integer office);

}