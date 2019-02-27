package com.cloudwing.checkstand.user.service;

import com.cloudwing.checkstand.common.result.BaseResult;
import com.cloudwing.checkstand.user.dto.RadioDto;
import com.cloudwing.checkstand.user.entity.User;
import com.cloudwing.checkstand.user.vo.UserEmpowerVo;
import com.cloudwing.checkstand.user.vo.UserQueryVo;
import com.github.pagehelper.PageInfo;
import com.yunyitg.rpc.base.exception.RpcException;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public interface UserService {

    /**
     * 通过账号密码登录
     * @param name
     * @param password
     * @return
     */
    User login(String name, String password);

    /**
     * 通过云翼外贸圈平台用户id登录
     * @param token
     * @param appSessKey
     * @return
     */
    User platformLogin(String token, String appSessKey) throws InterruptedException,ExecutionException,TimeoutException,RpcException;

    /**
     * 根据用户id获取用户角色role 集合
     * @param userId
     * @return
     */
    Set<String> listRolesByUserId(Integer userId);

    /**
     * 根据用户id获取用户权限permission字段【url】集合
     * @param user
     * @return
     */
    Set<String> listPermissionsByUserId(User user);

    /**
     * 根据用户id更新用户信息
     * @param user
     * @return
     */
    int updateById(User user);

    /**
     * 根据用户id获取用户关联现场
     * @param userId
     * @return
     */
    List<RadioDto> listOfficeByUserId(Integer userId);

    /**
     * 根据权限拿分页数据
     * @param user
     * @return
     */
    BaseResult<PageInfo<UserQueryVo>> listPage(User user, int page, int limit);

    /**
     * 根据用户id和现场id获取关联商户信息
     * @param officeCode
     * @return
     */
    List<RadioDto> listMerchantByUserIdAndOfficeId(String officeCode);

    /**
     * 根据Id更新状态
     * @param id
     * @param status
     * @return
     */
    BaseResult updateStatusById(Integer id, String status);

    /**
     * 根据userId查询Office表里的数据,有没有跟userId关联的都查出来,然后用companyId过滤数据
     * @param userId
     * @param companyId
     * @param page
     * @param limit
     * @return
     */
    BaseResult<PageInfo<UserEmpowerVo>> listOperate(Integer userId, Integer companyId, int page, int limit);

    /**
     * 根据userId And officeId更新userOffice表
     * checked决定增删,true为增，false为删
     * @param userId
     * @param officeId
     * @param checked
     * @return
     */
    BaseResult operateUpdateByOfficeIdAndChecked(Integer userId, Integer officeId, boolean checked);
}
