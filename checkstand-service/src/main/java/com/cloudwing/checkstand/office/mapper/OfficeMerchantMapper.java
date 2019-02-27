package com.cloudwing.checkstand.office.mapper;

import com.cloudwing.checkstand.office.entity.Office;
import com.cloudwing.checkstand.office.entity.OfficeMerchant;
import org.apache.ibatis.annotations.Param;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public interface OfficeMerchantMapper {
    int insert(OfficeMerchant record);

    int insertSelective(OfficeMerchant record);

    /**
     * 根据oids查找对应的mid
     * @param oids
     * @return
     */
    Integer[] selectMidByOid(@Param("oids") Integer[] oids);

    /**
     * 根据companyId查找对应的数据
     * @param companyId
     * @return
     */
    List<Office> listOfficesByCompanyId(@Param("companyId") String companyId);

    HashSet<Integer> listMidByOid(@Param("officeId")Integer officeId);
}