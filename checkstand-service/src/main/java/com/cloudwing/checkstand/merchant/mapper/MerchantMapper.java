package com.cloudwing.checkstand.merchant.mapper;

import com.cloudwing.checkstand.merchant.entity.Merchant;
import com.cloudwing.checkstand.merchant.vo.MerchantQueryVo;
import com.cloudwing.checkstand.user.dto.RadioDto;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;

public interface MerchantMapper extends BaseMapper<Merchant> {
//    int deleteByPrimaryKey(Integer id);
//
//    int insert(Merchant record);
//
//    int insertSelective(Merchant record);
//
//    Merchant selectByPrimaryKey(Integer id);
//
//    int updateByPrimaryKeySelective(Merchant record);
//
//    int updateByPrimaryKey(Merchant record);

    List<RadioDto> listCodeAndNameByOfficeId(@Param("officeId")Integer officeId);

    /**
     * 根据mids And companyId查询对应的数据用MerchantQueryVo接收
     * @param mids
     * @param companyId 为null时不做判断
     * @return
     */
    List<MerchantQueryVo> listMerchantQueryVosByIdAndCompanyId(@Param("mids") Integer[] mids, @Param("companyId") Integer companyId);


    List<Merchant> listByCompanyId(@Param("companyId")Integer companyId);
}