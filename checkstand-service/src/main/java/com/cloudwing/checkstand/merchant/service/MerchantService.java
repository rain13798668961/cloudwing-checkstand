package com.cloudwing.checkstand.merchant.service;


import com.cloudwing.checkstand.common.result.BaseResult;
import com.cloudwing.checkstand.merchant.vo.MerchantQueryVo;
import com.cloudwing.checkstand.user.entity.User;
import com.github.pagehelper.PageInfo;

public interface MerchantService {
    /**
     * 根据权限拿分页数据
     * @param user
     * @param page
     * @param limit
     * @return
     */
    BaseResult<PageInfo<MerchantQueryVo>> listPage(User user, int page, int limit);
}
