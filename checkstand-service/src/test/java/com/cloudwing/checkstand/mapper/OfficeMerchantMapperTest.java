package com.cloudwing.checkstand.mapper;

import com.cloudwing.checkstand.office.mapper.OfficeMerchantMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Create by cjf on 2019/2/13.
 */
public class OfficeMerchantMapperTest extends BaseTest {
    @Autowired
    private OfficeMerchantMapper officeMerchantMapper;

    @Test
    public void selectMidByOid() {
        Integer[] oid = {1, 2};
        Integer[] mid = officeMerchantMapper.selectMidByOid(oid);
        System.out.println(mid);
    }
}
