package com.cloudwing.checkstand.office.service.impl;

import cn.hutool.core.date.DateUtil;
import com.cloudwing.checkstand.common.enums.BaseStatusEnum;
import com.cloudwing.checkstand.common.result.BaseResult;
import com.cloudwing.checkstand.common.utils.UserHelper;
import com.cloudwing.checkstand.merchant.entity.Merchant;
import com.cloudwing.checkstand.merchant.mapper.MerchantMapper;
import com.cloudwing.checkstand.merchant.service.impl.MerchantManager;
import com.cloudwing.checkstand.office.dto.OfficeMchSwitchDto;
import com.cloudwing.checkstand.office.dto.OfficeQueryDto;
import com.cloudwing.checkstand.office.entity.Office;
import com.cloudwing.checkstand.office.entity.OfficeMerchant;
import com.cloudwing.checkstand.office.mapper.OfficeMapper;
import com.cloudwing.checkstand.office.mapper.OfficeMerchantMapper;
import com.cloudwing.checkstand.office.service.OfficeService;
import com.cloudwing.checkstand.trade.entity.PaymentOrder;
import com.cloudwing.checkstand.user.entity.User;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class OfficeServiceImpl implements OfficeService {

    @Autowired
    private OfficeMapper officeMapper;

    @Autowired
    private OfficeManager officeManager;

    @Autowired
    private MerchantMapper merchantMapper;

    @Autowired
    private OfficeMerchantMapper officeMerchantMapper;


    @Override
    public BaseResult<PageInfo<Office>> listByConditions(User user, OfficeQueryDto officeQueryDto) {

        Example example = new Example(Office.class);
        Example.Criteria criteria = example.createCriteria();

        if (UserHelper.hasSuperAdminRoles(user.getRoles())) { // 超级管理员

        } else if(UserHelper.hasAdminRoles(user.getRoles())) {  //企业管理员
            if (null != user.getCompanyId()) {
                criteria.andEqualTo("companyId", user.getCompanyId());
            }
        } else {
            return new BaseResult<PageInfo<Office>>("10000","获取成功",new PageInfo<Office>(new ArrayList<Office>()));
        }
        // 现场名称
        if (StringUtils.hasText(officeQueryDto.getName())) {
            criteria.andEqualTo("name", officeQueryDto.getName());
        }
        // 现场代码
        if (StringUtils.hasText(officeQueryDto.getCode())) {
            criteria.andEqualTo("code", officeQueryDto.getCode());
        }
        example.orderBy("id").desc();
        Integer page = officeQueryDto.getPage() == null ? 1 : officeQueryDto.getPage();  //页码
        Integer limit =  officeQueryDto.getLimit() == null ? 10 : officeQueryDto.getLimit();  //每页数量
        PageHelper.startPage(page, limit);
        List<Office> l = officeMapper.selectByExample(example);


        PageInfo<Office> pageInfo = new PageInfo<Office>(l);
        return new BaseResult<PageInfo<Office>>("10000","获取成功",pageInfo);
    }

    @Override
    @Transactional
    public BaseResult<Object> createOffice(User user, Office office) {

        Integer companyId = user.getCompanyId();
        office.setCompanyId(companyId);
        office.setCreateUserId(user.getId());

        officeMapper.insert(office);
        return new BaseResult<Object>(BaseStatusEnum.SUCCESS);
    }

    @Override
    public BaseResult<Object> listRelatedMchByOfficeId(User user, String officeCode) {

        List<Merchant> merchantList = merchantMapper.listByCompanyId(user.getCompanyId());
        Integer officeId = officeManager.getIdByCode(officeCode);

        HashSet<Integer> mchIds = officeMerchantMapper.listMidByOid(officeId);

        List<OfficeMchSwitchDto> officeMchSwitchDtos = new ArrayList<OfficeMchSwitchDto>();
        for (Merchant m : merchantList) {
            OfficeMchSwitchDto dto = new OfficeMchSwitchDto();
            dto.setMerchantName(m.getName());
            dto.setMerchantCode(m.getCwMerchantCode());
            dto.setIsRelated("N");
            if (mchIds.contains(m.getId())) {
                dto.setIsRelated("Y");
            }
            officeMchSwitchDtos.add(dto);
        }
        return new BaseResult<Object>(BaseStatusEnum.SUCCESS.getStatus(),"操作成功", officeMchSwitchDtos);
    }
}
