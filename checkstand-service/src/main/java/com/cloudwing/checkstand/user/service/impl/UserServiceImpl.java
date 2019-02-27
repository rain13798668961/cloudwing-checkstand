package com.cloudwing.checkstand.user.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.cloudwing.checkstand.common.enums.PlatformRoleEnum;
import com.cloudwing.checkstand.common.enums.RoleEnum;
import com.cloudwing.checkstand.company.entity.Company;
import com.cloudwing.checkstand.company.service.CompanyService;
import com.cloudwing.checkstand.consumer.platform.beans.UserDetail;
import com.cloudwing.checkstand.common.enums.BaseStatusEnum;
import com.cloudwing.checkstand.common.exception.ValidateException;
import com.cloudwing.checkstand.merchant.mapper.MerchantMapper;
import com.cloudwing.checkstand.office.mapper.OfficeMapper;
import com.cloudwing.checkstand.office.service.impl.OfficeManager;
import com.cloudwing.checkstand.permission.entity.Permission;
import com.cloudwing.checkstand.permission.mapper.PermissionMapper;
import com.cloudwing.checkstand.permission.mapper.RoleMapper;
import cn.hutool.core.bean.BeanUtil;
import com.cloudwing.checkstand.common.result.BaseResult;
import com.cloudwing.checkstand.common.utils.UserHelper;
import com.cloudwing.checkstand.company.service.impl.CompanyManager;
import com.cloudwing.checkstand.permission.service.RoleService;
import com.cloudwing.checkstand.permission.service.impl.RoleManager;
import com.cloudwing.checkstand.user.entity.UserRole;
import com.cloudwing.checkstand.user.mapper.UserOfficeMapper;
import com.cloudwing.checkstand.user.mapper.UserRoleMapper;
import com.cloudwing.checkstand.user.vo.UserEmpowerVo;
import com.cloudwing.checkstand.user.vo.UserQueryVo;
import com.cloudwing.checkstand.user.dto.RadioDto;
import com.cloudwing.checkstand.user.entity.User;
import com.cloudwing.checkstand.user.mapper.UserMapper;
import com.cloudwing.checkstand.user.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yunyitg.rpc.base.exception.RpcException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;


@Service
public class UserServiceImpl implements UserService {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CompanyManager companyManager;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private RoleManager roleManager;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private OfficeMapper officeMapper;
    @Autowired
    private OfficeManager officeManager;
    @Autowired
    private PermissionMapper permissionMapper;
    @Autowired
    private MerchantMapper merchantMapper;
    @Autowired
    private UserOfficeMapper userOfficeMapper;
    @Autowired
    private com.cloudwing.checkstand.consumer.platform.UserService platformUserService;



    @Override
    public User login(String account, String password) {

        return userMapper.findByAccountAndPassword(account, password);
    }

    @Override
    @Transactional
    public User platformLogin(String token, String appSessKey) throws InterruptedException,ExecutionException,TimeoutException,RpcException {

        String jsonInfo = platformUserService.authToken(token);

        if (!"false".equals(jsonInfo)) {
            JSONObject jsonObject = JSONObject.parseObject(jsonInfo);
            String platUserId = jsonObject.getString("user_id");
            String platServiceId = jsonObject.getString("service_id");
            String platSessionId = jsonObject.getString("sessionid");
            String platAppSessKey = jsonObject.getString("app_sess_key");

            if (!appSessKey.equals(platAppSessKey)) {  // appSessKey不匹配
                log.info("平台用户服务ahthToken方法中app_sess_key匹配失败");
                return null;
            }
            User u = userMapper.findByPlatformUserId(Integer.valueOf(platUserId));

            if (u == null) {  //用户不存在本应用
                UserDetail userDetail = platformUserService.getUserInfo(Integer.valueOf(platUserId));
                Company company = companyService.lookupByPlatformCompanyId(userDetail.getCompany_id());

                User newUser = exchangeToUser(userDetail, company.getId());
                userMapper.insert(newUser);

                //新用户赋予角色
                configureUserRole(newUser.getId(), userDetail.getRole_code());
                newUser.setSessionid(platSessionId);
                return newUser;
            } else {
                u.setSessionid(platSessionId);
                return u;
            }
        } else {
            return null;
        }
    }

    private void configureUserRole(Integer userId, String platformUserRole) {
        UserRole userRole = new UserRole();
        userRole.setUid(userId);
        //超级平台管理员和平台管理员
        if(PlatformRoleEnum.PLATFORMADMIN.getCode().equals(platformUserRole) ||
                PlatformRoleEnum.PLATFORMORDINARY.getCode().equals(platformUserRole)) {

            userRole.setRid(roleManager.getCodeMapRole().get(RoleEnum.SuperAdmin.getCode()).getId());
        }
        // 主账号（企业管理员）
        else if(PlatformRoleEnum.ADMIN.getCode().equals(platformUserRole)) {
            userRole.setRid(roleManager.getCodeMapRole().get(RoleEnum.Admin.getCode()).getId());
        }
        // 普通账号（企业操作员）
        else if (PlatformRoleEnum.ORDINARY.getCode().equals(platformUserRole)) {
            userRole.setRid(roleManager.getCodeMapRole().get(RoleEnum.OfficeOperator.getCode()).getId());
        } else {
            userRole.setRid(roleManager.getCodeMapRole().get(RoleEnum.Obeserver.getCode()).getId());
        }
        userRoleMapper.insert(userRole);
    }

    private User exchangeToUser(UserDetail userDetail, Integer  companyId) {
        User u = new User();
        u.setCompanyId(companyId);
        u.setPlatformUserId(userDetail.getId());
        u.setPlatformCompanyId(Integer.valueOf(userDetail.getCompany_id()));
        u.setRoleId(Integer.valueOf(userDetail.getRole_id()));
        u.setName(userDetail.getName());
        u.setAccount(userDetail.getAccount());
        u.setPhone(userDetail.getPhone());
        u.setEmail(userDetail.getEmail());
        u.setAvatar(userDetail.getAvatar());
        u.setAvatarType(userDetail.getAvatar_type());
        u.setStatus(userDetail.getStatus());
//        u.setNote(userDetail.getN);

        return u;
    }

    @Override
    public Set<String> listRolesByUserId(Integer userId) {
        return roleMapper.listCodeByUserId(userId);
    }

    @Override
    public Set<String> listPermissionsByUserId(User user) {
        // 若用户拥有超级管理员角色，用户拥有该系统所有权限
        if (UserHelper.hasSuperAdminRoles(user.getRoles())) {
            log.info("当前用户为超级管理员，用户id:" + user.getId());
            List<Permission> perms = permissionMapper.selectAll();
            Set<String> permUrls = new HashSet<String>();
            for (Permission p : perms) {
                if (null != p.getUrl())
                    permUrls.add(p.getUrl());
            }
            return permUrls;
        }
        return permissionMapper.listUrlByUserId(user.getId());
    }

    @Override
    public int updateById(User user) {
        return userMapper.updateByPrimaryKeySelective(user);
    }


    public BaseResult<PageInfo<UserQueryVo>> listPage(User user, int page, int limit) {
        Set<String> roles = user.getRoles();
        PageInfo<UserQueryVo> userPageInfo;
        List<UserQueryVo> userQueryVos = new ArrayList<>();
        if (UserHelper.hasSuperAdminRoles(roles)) { //超级管理员
            // 获取所有用户数据
            PageHelper.startPage(page, limit).setOrderBy("id desc");
            userQueryVos = userMapper.listUserQueryVo();
            userPageInfo = new PageInfo<>(userQueryVos);
        } else if (UserHelper.hasAdminRoles(roles)) { //管理员
            // 获取对应企业下的所有用户数据
            PageHelper.startPage(page, limit).setOrderBy("id desc");
            userQueryVos = userMapper.listUserQueryVoByCompanyId(user.getCompanyId());
            userPageInfo = new PageInfo<>(userQueryVos);
        } else if (UserHelper.hasOfficeLeaderRoles(roles)) { // 现场负责人
            // 获取所属现场所有用户数据
            Integer[] oids = userOfficeMapper.selectOidByUid(user.getId());
            Integer[] uids = userOfficeMapper.selectUidByOid(oids);
            PageHelper.startPage(page, limit).setOrderBy("id desc");
            userQueryVos = userMapper.listUserQueryVoByUid(uids);
            userPageInfo = new PageInfo<>(userQueryVos);
        } else { //操作员 or 观察者
            // 获取自己的数据
            userQueryVos.add(userMapper.getUserQueryVoById(user.getId()));
            userPageInfo = new PageInfo<>(userQueryVos);
        }
        log.info("userPageInfo分页数据:  " + userPageInfo);
        return new BaseResult<PageInfo<UserQueryVo>>(BaseStatusEnum.SUCCESS.getStatus(), BaseStatusEnum.SUCCESS.getMsg(), userPageInfo);
    }

    @Override
    public BaseResult updateStatusById(Integer id, String status) {
        User user = new User();
        user.setId(id);
        user.setStatus(status);
        int resultStatus = userMapper.updateByPrimaryKeySelective(user);
        if (resultStatus == 1) {
            return new BaseResult(BaseStatusEnum.SUCCESS.getStatus(), BaseStatusEnum.SUCCESS.getMsg(), null);
        }
        return new BaseResult(BaseStatusEnum.FAILED.getStatus(), BaseStatusEnum.FAILED.getMsg(), null);

    }

    @Override
    public BaseResult<PageInfo<UserEmpowerVo>> listOperate(Integer userId, Integer companyId, int page, int limit) {
        PageInfo<UserEmpowerVo> userEmpowerVoPageInfo;
        PageHelper.startPage(page, limit);
        List<UserEmpowerVo> userEmpowerVos = officeMapper.listUserEmpowerVosByUserIdAndCompanyId(userId, companyId);
        for (UserEmpowerVo userEmpowerVo : userEmpowerVos) {
            userEmpowerVo.setCompanyName(companyManager.getCompanyNameById(userEmpowerVo.getCompanyId()));
        }
        userEmpowerVoPageInfo = new PageInfo<>(userEmpowerVos);
        log.info("userEmpowerVoPageInfo分页数据:  " + userEmpowerVoPageInfo);
        return new BaseResult<PageInfo<UserEmpowerVo>>(BaseStatusEnum.SUCCESS.getStatus(), BaseStatusEnum.SUCCESS.getMsg(), userEmpowerVoPageInfo);
    }

    @Override
    public BaseResult operateUpdateByOfficeIdAndChecked(Integer userId, Integer officeId, boolean checked) {
        int resultStatus = 0;
        if (checked) {
            resultStatus = userOfficeMapper.saveByUserIdAndOfficeId(userId, officeId);
        } else {
            resultStatus = userOfficeMapper.removeByUserIdAndOfficeId(userId, officeId);
        }
        if (resultStatus == 1) {
            return new BaseResult(BaseStatusEnum.SUCCESS.getStatus(), BaseStatusEnum.SUCCESS.getMsg(), null);
        }
        return new BaseResult(BaseStatusEnum.FAILED.getStatus(), BaseStatusEnum.FAILED.getMsg(), null);
    }


    /**
     * 将数据库表Bean转换为返回前端时的Bean
     * @param userList
     * @return
     */
    public  List<UserQueryVo> userToUserQueryVo (List<User> userList) {
        List<UserQueryVo> userQueryVoList = new ArrayList<>();
        if (null != userList && userList.size() != 0) {
            for (User user : userList) {
                UserQueryVo userQueryVo = BeanUtil.toBean(user, UserQueryVo.class);
                userQueryVo.setCompanyName(companyManager.getCompanyNameById(userQueryVo.getCompanyId()));
//                userQueryVo.setPassword(null);// 密码不返回
                userQueryVo.setDescription(roleService.roleDescriptionByUserId(userQueryVo.getId()));
                userQueryVoList.add(userQueryVo);
            }
            return userQueryVoList;
        }
        return null;
    }

    @Override
    public List<RadioDto> listOfficeByUserId(Integer userId) {
        return officeMapper.listCodeAndNameByUserId(userId);
    }

    @Override
    public List<RadioDto> listMerchantByUserIdAndOfficeId(String officeCode) {
        Integer officeId = officeManager.getIdByCode(officeCode);
        return merchantMapper.listCodeAndNameByOfficeId(officeId);
    }


}
