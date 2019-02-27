package com.cloudwing.checkstand.web;

import com.cloudwing.checkstand.common.result.BaseResult;
import com.cloudwing.checkstand.common.utils.FastJsonUtils;
import com.cloudwing.checkstand.consumer.pay.dto.PayResult;
import com.cloudwing.checkstand.trade.service.PaymentOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/out")
public class CallbackController {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PaymentOrderService paymentOrderService;


    @RequestMapping(value = "/pay/callback", method=RequestMethod.POST)
    public BaseResult<Object> payCallback(PayResult result) {
        LOG.info("invoke----------/out/pay/callback");

        LOG.info("回调内容：" + FastJsonUtils.toJSONString(result));


        return paymentOrderService.notifyOrder(result);
    }


}
