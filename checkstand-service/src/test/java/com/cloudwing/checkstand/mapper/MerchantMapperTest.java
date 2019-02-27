package com.cloudwing.checkstand.mapper;

import com.cloudwing.checkstand.common.utils.FastJsonUtils;
import com.cloudwing.checkstand.merchant.mapper.MerchantMapper;
import com.cloudwing.checkstand.merchant.vo.MerchantQueryVo;
import com.cloudwing.checkstand.office.mapper.OfficeMerchantMapper;
import com.cloudwing.checkstand.user.dto.RadioDto;
import com.cloudwing.checkstand.user.mapper.UserOfficeMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MerchantMapperTest extends BaseTest{

    @Autowired
    private MerchantMapper merchantMapper;
    @Autowired
    private UserOfficeMapper userOfficeMapper;
    @Autowired
    private OfficeMerchantMapper officeMerchantMapper;

    @Test
    public void test() {
        List<RadioDto> l = merchantMapper.listCodeAndNameByOfficeId(4);

        for (RadioDto d : l) {
            System.out.println(FastJsonUtils.toJSONString(d));
        }
    }

    @Test
    public void listByIdAndCompanyIdTest() {
        Integer[] oid = userOfficeMapper.selectOidByUid(1);
        Integer[] mid = officeMerchantMapper.selectMidByOid(oid);
        List<MerchantQueryVo> merchantQueryVoList = merchantMapper.listMerchantQueryVosByIdAndCompanyId(mid, null);
        for (MerchantQueryVo merchantQueryVo : merchantQueryVoList) {
            System.out.println(merchantQueryVo.toString());
        }
    }
}
