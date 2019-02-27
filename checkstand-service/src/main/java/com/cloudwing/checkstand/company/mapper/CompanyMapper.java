package com.cloudwing.checkstand.company.mapper;

import com.cloudwing.checkstand.company.entity.Company;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.BaseMapper;

public interface CompanyMapper extends BaseMapper<Company> {
//    int deleteByPrimaryKey(Integer id);
//
//    int insert(Company record);
//
//    int insertSelective(Company record);
//
//    Company selectByPrimaryKey(Integer id);
//
//    int updateByPrimaryKeySelective(Company record);
//
//    int updateByPrimaryKey(Company record);

    /**
     * 根据云翼外贸圈平台企业id查找企业
     * @param platformCompanyId
     * @return
     */
    Company findByPlatformCompanyId(@Param("platformCompanyId") Integer platformCompanyId);
}