package com.cloudwing.checkstand.office.service;

import com.cloudwing.checkstand.common.result.BaseResult;
import com.cloudwing.checkstand.office.dto.OfficeQueryDto;
import com.cloudwing.checkstand.office.entity.Office;
import com.cloudwing.checkstand.user.entity.User;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface OfficeService {

    /**
     * 获取现场列表
     * @param user
     * @param officeQueryDto
     * @return
     */
    BaseResult<PageInfo<Office>> listByConditions(User user, OfficeQueryDto officeQueryDto);

    /**
     * 新建现场
     * @param user
     * @param office
     * @return
     */
    BaseResult<Object> createOffice(User user, Office office);


    /**
     *
     * @param user
     * @param officeCode
     * @return
     */
    BaseResult<Object> listRelatedMchByOfficeId(User user, String officeCode);
}
