package com.cloudwing.checkstand.mapper;

import com.cloudwing.checkstand.common.utils.FastJsonUtils;
import com.cloudwing.checkstand.trade.entity.PaymentOrder;
import com.cloudwing.checkstand.trade.mapper.PaymentOrderMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class PaymentOrderMapperTest  extends BaseTest {

    @Autowired
    PaymentOrderMapper paymentOrderMapper;

    @Test
    public void testFindByOrderNo() {

        PaymentOrder p = paymentOrderMapper.findByOrderNo("2019012916330900001");
        System.out.println(FastJsonUtils.toJSONString(p));
    }
}
