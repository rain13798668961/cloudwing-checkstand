package com.cloudwing.checkstand.user.mapper;

import com.cloudwing.checkstand.user.dto.RadioDto;
import com.cloudwing.checkstand.user.entity.User;
import com.cloudwing.checkstand.user.vo.UserQueryVo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;

//@Mapper
public interface UserMapper extends BaseMapper<User> {
//    int deleteByPrimaryKey(Integer id);
//
//    int insert(User record);
//
//    int insertSelective(User record);
//
//    User selectByPrimaryKey(Integer id);
//
//    int updateByPrimaryKeySelective(User record);
//
//    int updateByPrimaryKey(User record);

    /**
     * 根据账号密码查找用户
     * 用于登录校验
     * @param account
     * @param password
     * @return
     */
    User findByAccountAndPassword(@Param("account")String account, @Param("password")String password);


    /**
     * 根据云翼外贸圈平台用户id查找用户
     * @param platformUserId
     * @return
     */
    User findByPlatformUserId(@Param("platformUserId")Integer platformUserId);

    /**
     * 根据用户id查找关联现场[office.code,office.name]集合
     * @param userId
     * @return
     */
    List<RadioDto> listOfficeByUserId(@Param("userId")Integer userId);

    /**
     * 根据companyId查找对应的数据用UserQueryVo接收
     * @param companyId
     * @return
     */
    List<UserQueryVo> listUserQueryVoByCompanyId(@Param("companyId") Integer companyId);

    /**
     * 根据userId查询对应的officeId(有可能多个)
     * 然后根据officeId查出userId，再通过userId拿User数据
     * @param userId
     * @return
     */
    List<User> listByUserIdSelectOfficeId(@Param("userId") Integer userId);

    /**
     * 根据userId查询对应的数据用UserQueryVo接收
     * @param uids
     * @return
     */
    List<UserQueryVo> listUserQueryVoByUid(@Param("uids") Integer[] uids);

    /**
     * 查询所有user数据用UserQueryVo接收
     * @return
     */
    List<UserQueryVo> listUserQueryVo();

    /**
     * 根据id查询所有user数据用UserQueryVo接收
     * @return
     */
    UserQueryVo getUserQueryVoById(@Param("id")Integer id);
}