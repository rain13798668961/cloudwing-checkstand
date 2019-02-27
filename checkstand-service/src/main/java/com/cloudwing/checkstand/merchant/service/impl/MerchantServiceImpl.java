package com.cloudwing.checkstand.merchant.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.cloudwing.checkstand.common.result.BaseResult;
import com.cloudwing.checkstand.common.utils.UserHelper;
import com.cloudwing.checkstand.company.service.impl.CompanyManager;
import com.cloudwing.checkstand.merchant.entity.Merchant;
import com.cloudwing.checkstand.merchant.mapper.MerchantMapper;
import com.cloudwing.checkstand.merchant.service.MerchantService;
import com.cloudwing.checkstand.merchant.vo.MerchantQueryVo;
import com.cloudwing.checkstand.office.mapper.OfficeMerchantMapper;
import com.cloudwing.checkstand.user.entity.User;
import com.cloudwing.checkstand.user.mapper.UserOfficeMapper;
import com.cloudwing.checkstand.user.vo.UserQueryVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Create by cjf on 2019/2/12.
 */
@Service
public class MerchantServiceImpl implements MerchantService {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private MerchantMapper merchantMapper;
    @Autowired
    private CompanyManager companyManager;
    @Autowired
    private UserOfficeMapper userOfficeMapper;
    @Autowired
    private OfficeMerchantMapper officeMerchantMapper;

    @Override
    public BaseResult<PageInfo<MerchantQueryVo>> listPage(User user, int page, int limit) {
        Set<String> roles = user.getRoles();
        PageInfo<MerchantQueryVo> merchantPageInfo = null;
        List<MerchantQueryVo> merchantQueryVoList = new ArrayList<>();
        Integer[] oid = userOfficeMapper.selectOidByUid(user.getId());
        Integer[] mid = officeMerchantMapper.selectMidByOid(oid);
        PageHelper.startPage(page, limit).setOrderBy("id desc");
        if (UserHelper.hasSuperAdminRoles(roles)) { //超级管理员
            merchantQueryVoList = merchantMapper.listMerchantQueryVosByIdAndCompanyId(mid, null);
            merchantPageInfo = new PageInfo<>(merchantQueryVoList);
        } else if (UserHelper.hasAdminRoles(roles)) { //管理员
            merchantQueryVoList = merchantMapper.listMerchantQueryVosByIdAndCompanyId(mid, user.getCompanyId());
            merchantPageInfo = new PageInfo<>(merchantQueryVoList);
        }
        log.info("merchantPageInfo分页数据:  " + merchantPageInfo);
        return new BaseResult<PageInfo<MerchantQueryVo>>("10000", "userList成功", merchantPageInfo);
    }

}
