package com.cloudwing.checkstand.merchant.service.impl;

import com.cloudwing.checkstand.merchant.entity.Merchant;
import com.cloudwing.checkstand.merchant.mapper.MerchantMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MerchantManager {

    @Autowired
    private MerchantMapper merchantMapper;

    @Cacheable
    public List<Merchant> listAllMerchant() {
        return merchantMapper.selectAll();
    }

    /**
     * 获取商户数据表主键id对应商户实体集合
     * @return
     */
    public Map<Integer, Merchant> getIdMapMerchant() {
        Map<Integer, Merchant> idMapMerchant = new HashMap<Integer, Merchant>();
        List<Merchant> list = listAllMerchant();
        for (Merchant mch : list) {
            idMapMerchant.put(mch.getId(), mch);
        }
        return idMapMerchant;
    }

    /**
     * 根据主键id获取商户名称
     * @param mchId
     * @return
     */
    public String getMerchantNameById(Integer mchId) {
        Merchant mch = getIdMapMerchant().get(mchId);
        if (null != mch) {
            return mch.getName();
        }
        return null;
    }

    /**
     * 获取商户代码code对应商户实体集合
     * @return
     */
    public Map<String, Merchant> getCodeMapMerchant() {
        Map<String, Merchant> codeMapMerchant = new HashMap<String, Merchant>();
        List<Merchant> list = listAllMerchant();
        for (Merchant mch : list) {
            codeMapMerchant.put(mch.getCwMerchantCode(), mch);
        }
        return codeMapMerchant;
    }

    /**
     * 根据商户代码code获取商户主键id
     * @param code
     * @return
     */
    public Integer getIdByCode(String code) {
        Merchant mch = getCodeMapMerchant().get(code);
        if (null != mch) {
            return mch.getId();
        }
        return null;
    }

}
