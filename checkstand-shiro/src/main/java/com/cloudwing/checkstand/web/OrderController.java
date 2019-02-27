package com.cloudwing.checkstand.web;

import com.cloudwing.checkstand.common.enums.BaseStatusEnum;
import com.cloudwing.checkstand.common.result.BaseResult;
import com.cloudwing.checkstand.common.utils.FastJsonUtils;
import com.cloudwing.checkstand.shiro.token.manager.TokenMananger;
import com.cloudwing.checkstand.trade.dto.OrderPlaceDto;
import com.cloudwing.checkstand.trade.dto.OrderQueryDto;
import com.cloudwing.checkstand.trade.entity.PaymentOrder;
import com.cloudwing.checkstand.trade.service.PaymentOrderService;
import com.cloudwing.checkstand.user.dto.RadioDto;
import com.cloudwing.checkstand.user.entity.User;
import com.cloudwing.checkstand.user.service.UserService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PaymentOrderService orderService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/list", method=RequestMethod.POST)
    public BaseResult<PageInfo<PaymentOrder>> orderList(OrderQueryDto orderQueryDTO) {
        log.info("请求参数：" +FastJsonUtils.toJSONString(orderQueryDTO));
        return orderService.listByConditions(TokenMananger.getToken(),orderQueryDTO);
    }

    @RequestMapping(value = "/office/list", method=RequestMethod.POST)
    public BaseResult<List<RadioDto>> officeList() {

        log.info("................invoke.. /order/office/list");
        User token = TokenMananger.getToken();
        List<RadioDto> dtos = userService.listOfficeByUserId(token.getId());

//        log.info(FastJsonUtils.toJSONString(dtos));
        return new BaseResult<List<RadioDto>>(BaseStatusEnum.SUCCESS.getStatus(),
                BaseStatusEnum.SUCCESS.getMsg(), dtos);
    }

    @RequestMapping(value = "/mch/list", method=RequestMethod.POST)
    public BaseResult<List<RadioDto>> mchList(@RequestParam(value = "officeCode")String officeCode) {
        log.info("................invoke.. /order/mch/list");
        List<RadioDto> dtos = userService.listMerchantByUserIdAndOfficeId(officeCode);
        return new BaseResult<List<RadioDto>>(BaseStatusEnum.SUCCESS.getStatus(),
                BaseStatusEnum.SUCCESS.getMsg(), dtos);
    }

    /**
     * 下单（新建支付订单）
     * @param orderPlaceDto
     * @return
     */
    @RequestMapping(value = "/place", method = RequestMethod.POST)
    public BaseResult<Object> placeOrder(OrderPlaceDto orderPlaceDto) {

        return orderService.placeOrder(TokenMananger.getToken(), orderPlaceDto);
    }

    /**
     * 付款码支付提交
     * @param orderNo
     * @param authCode
     * @return
     */
    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public BaseResult<Object> submitOrder(@RequestParam(value = "orderNo")String orderNo,
                                          @RequestParam(value = "authCode")String authCode) {

        return orderService.submitOrder(TokenMananger.getToken(),orderNo, authCode);
    }



}
