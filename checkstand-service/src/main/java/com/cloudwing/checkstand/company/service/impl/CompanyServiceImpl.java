package com.cloudwing.checkstand.company.service.impl;

import com.cloudwing.checkstand.company.entity.Company;
import com.cloudwing.checkstand.company.mapper.CompanyMapper;
import com.cloudwing.checkstand.company.service.CompanyService;
import com.cloudwing.checkstand.consumer.platform.beans.CompanyDetail;
import com.yunyitg.rpc.base.exception.RpcException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyMapper companyMapper;

    @Autowired
    private com.cloudwing.checkstand.consumer.platform.CompanyService platformCompanyService;


    @Override
    @Transactional
    public Company lookupByPlatformCompanyId(String platformCompanyId) throws InterruptedException,ExecutionException,TimeoutException,RpcException {

        Company company = companyMapper.findByPlatformCompanyId(Integer.valueOf(platformCompanyId));

        if (company == null) { // 该企业不存在本应用
            CompanyDetail companyDetail = platformCompanyService.getCompanyInfo(Integer.valueOf(platformCompanyId));

            Company newCompany = exchangeToCompany(companyDetail);
            companyMapper.insert(newCompany);
            return newCompany;
        } else {
            return company;
        }
    }


    private Company exchangeToCompany(CompanyDetail companyDetail) {
        Company c = new Company();
        c.setName(companyDetail.getCompany_name());
        c.setCompanyCode(companyDetail.getCompany_code());
        c.setPlatformCompanyId(companyDetail.getId());
        c.setRegisterName(companyDetail.getRegister_name());
        c.setRegisterIdentityCode(companyDetail.getRegister_identity_code());
//        c.setRegisterTel(companyDetail.get);
        c.setStatus(companyDetail.getStatus());

        return c;
    }

}
