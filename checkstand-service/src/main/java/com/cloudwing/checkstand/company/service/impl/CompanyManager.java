package com.cloudwing.checkstand.company.service.impl;

import com.cloudwing.checkstand.company.entity.Company;
import com.cloudwing.checkstand.company.mapper.CompanyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CompanyManager {

    @Autowired
    private CompanyMapper companyMapper;


    @Cacheable(value = "companyCache")
    public List<Company> listAllCompany() {
        return companyMapper.selectAll();
    }

    /**
     * 获取企业数据表主键id对应企业实体集合
     * @return
     */
    public Map<Integer, Company> getIdMapCompany() {
        Map<Integer, Company> idMapCompany = new HashMap<Integer, Company>();
        List<Company> list = listAllCompany();
        for (Company com : list) {
            idMapCompany.put(com.getId(), com);
        }
        return idMapCompany;
    }

    /**
     * 根据主键id获取企业名称
     * @param companyId
     * @return
     */
    public String getCompanyNameById(Integer companyId) {
        Company com = getIdMapCompany().get(companyId);
        if (null != com) {
            return com.getName();
        } else {
            Company company = companyMapper.selectByPrimaryKey(companyId);
            if (company != null) {
                return company.getName();
            }
        }
        return null;
    }
}
