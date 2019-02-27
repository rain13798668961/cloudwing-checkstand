package com.cloudwing.checkstand.common.utils;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;

import java.util.Date;

/**
 * 订单编号 生成器
 */
public class OrderCreater {

    private static int randomNo = 0;

    private static synchronized String getRandomNo(){
        randomNo ++;
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(randomNo  >= 100000){
            randomNo = 1;
        }
        int needNo = randomNo;
        if(needNo < 10){
            return "0000" + needNo;
        }else if(needNo < 100){
            return "000" + needNo;
        }else if(needNo < 1000){
            return "00" + needNo;
        }else if(needNo < 10000){
            return "0" + needNo;
        }else {
            return "" + needNo;
        }

    }

    public static synchronized String create() {

        return DateUtil.format(new Date(), DatePattern.PURE_DATETIME_PATTERN) + getRandomNo();
    }

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            System.out.println(create());
        }
    }
}
