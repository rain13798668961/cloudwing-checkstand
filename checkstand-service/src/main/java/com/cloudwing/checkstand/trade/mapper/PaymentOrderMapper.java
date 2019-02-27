package com.cloudwing.checkstand.trade.mapper;

import com.cloudwing.checkstand.common.mapper.MyMapper;
import com.cloudwing.checkstand.trade.entity.PaymentOrder;
import org.apache.ibatis.annotations.Param;

public interface PaymentOrderMapper extends MyMapper<PaymentOrder> {
//    int deleteByPrimaryKey(Integer id);
//
//    int insert(PaymentOrder record);
//
//    int insertSelective(PaymentOrder record);
//
//    PaymentOrder selectByPrimaryKey(Integer id);
//
//    int updateByPrimaryKeySelective(PaymentOrder record);
//
//    int updateByPrimaryKey(PaymentOrder record);

    PaymentOrder findByOrderNo(@Param("orderNo")String orderNo);
}