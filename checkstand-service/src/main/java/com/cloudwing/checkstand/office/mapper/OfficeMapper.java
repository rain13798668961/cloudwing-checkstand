package com.cloudwing.checkstand.office.mapper;

import com.cloudwing.checkstand.common.mapper.MyMapper;
import com.cloudwing.checkstand.office.entity.Office;
import com.cloudwing.checkstand.user.dto.RadioDto;
import com.cloudwing.checkstand.user.vo.UserEmpowerVo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;

public interface OfficeMapper extends MyMapper<Office> {
//    int deleteByPrimaryKey(Integer id);
//
//    int insert(Office record);
//
//    int insertSelective(Office record);
//
//    Office selectByPrimaryKey(Integer id);
//
//    int updateByPrimaryKeySelective(Office record);
//
//    int updateByPrimaryKey(Office record);

    List<RadioDto> listCodeAndNameByUserId(@Param("userId")Integer userId);

    /**
     * 查询用户所属现场
     * @param userId
     * @return
     */
    List<Office> listByUserId(@Param("userId")Integer userId);

    /**
     * 根据companyId查询对应的数据
     * @param companyId
     * @return
     */
    List<Office> listByCompanyId(@Param("companyId")Integer companyId);

    /**
     * 根据userId查询Office表里的数据，有没有跟userId关联的都查出来，
     * 然后用companyId过滤数据
     * @param userId
     * @param companyId
     * @return
     */
    List<UserEmpowerVo> listUserEmpowerVosByUserIdAndCompanyId(@Param("userId") Integer userId, @Param("companyId") Integer companyId);
}