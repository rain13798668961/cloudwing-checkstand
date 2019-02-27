package com.cloudwing.checkstand.trade.mapper;

import com.cloudwing.checkstand.trade.entity.PaymentOrderRecord;

public interface PaymentOrderRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PaymentOrderRecord record);

    int insertSelective(PaymentOrderRecord record);

    PaymentOrderRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PaymentOrderRecord record);

    int updateByPrimaryKeyWithBLOBs(PaymentOrderRecord record);

    int updateByPrimaryKey(PaymentOrderRecord record);
}