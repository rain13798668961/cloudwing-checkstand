package com.cloudwing.checkstand.trade.service.impl;

import cn.hutool.core.date.DateUtil;
import com.cloudwing.checkstand.common.enums.BaseStatusEnum;
import com.cloudwing.checkstand.common.enums.PayStatusEnum;
import com.cloudwing.checkstand.common.result.BaseResult;
import com.cloudwing.checkstand.common.utils.OrderCreater;
import com.cloudwing.checkstand.common.utils.UserHelper;
import com.cloudwing.checkstand.merchant.entity.Merchant;
import com.cloudwing.checkstand.merchant.service.impl.MerchantManager;
import com.cloudwing.checkstand.office.service.impl.OfficeManager;
import com.cloudwing.checkstand.consumer.pay.PayService;
import com.cloudwing.checkstand.consumer.pay.dto.PayResult;
import com.cloudwing.checkstand.trade.dto.OrderPlaceDto;
import com.cloudwing.checkstand.trade.dto.OrderQueryDto;
import com.cloudwing.checkstand.trade.entity.PaymentOrder;
import com.cloudwing.checkstand.trade.mapper.PaymentOrderMapper;
import com.cloudwing.checkstand.trade.service.PaymentOrderService;
import com.cloudwing.checkstand.user.entity.User;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yunyitg.rpc.base.exception.RpcException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

@Service
public class PaymentOrderServiceImpl implements PaymentOrderService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PaymentOrderMapper paymentOrderMapper;

    @Autowired
    private OfficeManager officeManager;

    @Autowired
    private MerchantManager merchantManager;

    @Autowired
    private PayService payService;


    @Override
    public BaseResult<PageInfo<PaymentOrder>> listByConditions(@NotNull User user, OrderQueryDto orderQueryDto) {

        Example example = new Example(PaymentOrder.class);

        Example.Criteria criteria = example.createCriteria();

        if(UserHelper.hasSuperAdminRoles(user.getRoles())) {  //超级管理员

        } else if(UserHelper.hasAdminRoles(user.getRoles())) {  // 管理员
            // 企业id
            if (null != user.getCompanyId()) {
                criteria.andEqualTo("companyId", user.getCompanyId());
            }
        } else if(UserHelper.hasOfficeLeaderRoles(user.getRoles())) { // 现场负责人
            List<Integer> officeId = officeManager.getIdByUserId(user.getId());
            if(officeId.size() >=1) {
                criteria.andEqualTo("officeId", officeId.get(0));
            } else {
                criteria.andEqualTo("createUserId", user.getId());
            }
        }
        else {
            // 用户id
            if (null != user.getId()) {
                criteria.andEqualTo("createUserId", user.getId());
            }
        }

        // 创建时间起始
        if (null != orderQueryDto.getStartAt()) {
            criteria.andGreaterThan("createAt", DateUtil.formatDate(orderQueryDto.getStartAt()));
        }
        // 创建时间结束
        if (null != orderQueryDto.getEndAt()) {
            criteria.andLessThan("createAt", DateUtil.formatDate(orderQueryDto.getEndAt()));
        }
        // 订单编号
        if (StringUtils.hasText(orderQueryDto.getOrderNo())) {
            criteria.andEqualTo("orderNo", orderQueryDto.getOrderNo());
        }
        // 订单状态
        if (StringUtils.hasText(orderQueryDto.getStatus())) {
            criteria.andEqualTo("status", orderQueryDto.getStatus());
        }
        example.orderBy("id").desc();

        Integer page = orderQueryDto.getPage() == null ? 1 : orderQueryDto.getPage();  //页码
        Integer limit =  orderQueryDto.getLimit() == null ? 10 : orderQueryDto.getLimit();  //每页数量
        PageHelper.startPage(page, limit);
        List<PaymentOrder> l = paymentOrderMapper.selectByExample(example);

        PageInfo<PaymentOrder> pageInfo = new PageInfo<PaymentOrder>(l);
        return new BaseResult<PageInfo<PaymentOrder>>("10000","获取成功",pageInfo);
    }

    @Override
    @Transactional
    public BaseResult<Object> placeOrder(User user, OrderPlaceDto orderPlaceDto) {

        Integer officeId = officeManager.getIdByCode(orderPlaceDto.getOrderOfficeSelect());
        Integer mchId = merchantManager.getIdByCode(orderPlaceDto.getOrderMchSelect());

        PaymentOrder order = new PaymentOrder();
        order.setCompanyId(user.getCompanyId());
        order.setCreateUserId(user.getId());
        order.setOfficeId(officeId);
        order.setMerchantId(mchId);
        order.setOrderNo(OrderCreater.create());
        order.setOpType(orderPlaceDto.getOrderOpTypeSelect());
        order.setSubject(orderPlaceDto.getSubject());
        order.setBody(orderPlaceDto.getBody());
        order.setSum(orderPlaceDto.getSum());
        order.setNote(orderPlaceDto.getNote());
        order.setStatus(PayStatusEnum.NOTPAY.getStatus());

        paymentOrderMapper.insert(order);
        return new BaseResult<Object>(BaseStatusEnum.SUCCESS);
    }

    @Override
    public BaseResult<Object> submitOrder(User user, String orderNo, String authCode) {

        PaymentOrder paymentOrder = paymentOrderMapper.findByOrderNo(orderNo);
        if (paymentOrder == null || paymentOrder.getCompanyId() != user.getCompanyId()) {
            return new BaseResult<Object>(BaseStatusEnum.FAILED.getStatus(), "操作失败，您没有权限操作此订单");
        }
        if (PayStatusEnum.NOTPAY.getStatus() == paymentOrder.getStatus() ||
                PayStatusEnum.FAIL.getStatus() == paymentOrder.getStatus()) {

            Merchant merchant = merchantManager.getIdMapMerchant().get(paymentOrder.getMerchantId());
            PayResult result = null;
            try {
                result = payService.submit("cloudwing-checkstand", merchant.getCwMerchantCode(),
                        paymentOrder.getOrderNo(), paymentOrder.getSubject(), paymentOrder.getBody(), paymentOrder.getSum(),
                        paymentOrder.getNote(), authCode, "http://192.168.2.6:8080/out/pay/callback");

                if ("SUCCESS".equals(result.getTradeStatus())) {  //支付提交成功
                    paymentOrder.setStatus(PayStatusEnum.USERPAYING.getStatus());
                    paymentOrder.setTradeNo(result.getOutTradeNo());
                } else {
                    paymentOrder.setStatus(PayStatusEnum.FAIL.getStatus());
                }
                paymentOrder.setResult(result.getTradeMsg());
                paymentOrder.setUpdateUserId(user.getId());
                paymentOrder.setUpdateAt(new Date());
                paymentOrderMapper.updateByPrimaryKey(paymentOrder);
                return new BaseResult<Object>(BaseStatusEnum.SUCCESS);
            }catch (RpcException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
            paymentOrder.setStatus(PayStatusEnum.FAIL.getStatus());
            paymentOrder.setResult("远程支付服务调用失败，请联系管理员");
            paymentOrderMapper.updateByPrimaryKey(paymentOrder);
            return new BaseResult<Object>(BaseStatusEnum.FAILED.getStatus(), "远程支付服务调用失败，请联系管理员");
        } else {
            return new BaseResult<>(BaseStatusEnum.FAILED.getStatus(), "此订单当前状态不允许进行支付操作");
        }
    }

    @Override
    public BaseResult<Object> notifyOrder(PayResult payResult) {

        PaymentOrder paymentOrder = paymentOrderMapper.findByOrderNo(payResult.getTermNo());

        if ("SUCCESS".equals(payResult.getTradeStatus())) {
            paymentOrder.setStatus(PayStatusEnum.SUCCESS.getStatus());
        } else {
            paymentOrder.setStatus(PayStatusEnum.FAIL.getStatus());
        }
        paymentOrder.setResult(payResult.getTradeMsg());
        paymentOrder.setUpdateAt(new Date());
        paymentOrderMapper.updateByPrimaryKey(paymentOrder);
        return new BaseResult<Object>(BaseStatusEnum.SUCCESS);
    }
}
