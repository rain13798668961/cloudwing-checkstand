package com.cloudwing.checkstand.consumer.pay;

import com.cloudwing.checkstand.consumer.pay.PayService;
import com.cloudwing.checkstand.consumer.pay.PayServiceImpl;
import com.cloudwing.checkstand.consumer.pay.dto.PayResult;
import com.yunyitg.rpc.base.exception.RpcException;
import com.yunyitg.rpc.common.ThreadPool;
import com.yunyitg.rpc.common.YunyiRpcClientFactory;
import com.yunyitg.rpc.util.GsonUtil;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public class PayConsumerTest {

    public static void main(String[] args)  throws InterruptedException, ExecutionException, TimeoutException{
        //线上测试环境
        String host = "rpc.cw.com";
        int port = 9501;
        String name = "test";
        String token = "6QLuwr4QGZg0Gsq36LgphG8yIwzPHKBw";
        long timeoutms = 5*1000L;


        //建立工厂类
        YunyiRpcClientFactory factory = new YunyiRpcClientFactory(host, port, name, token, timeoutms);

        final PayService payService = new PayServiceImpl(factory);

        long costTimeStart = System.currentTimeMillis();
        PayResult result = null;

        try {
            result = payService.submit("cloudwing-checkstand", "KouAnSubMerchant", "201901110011", "rpc测试", "手机", 1, "test", "134725642282695493" , null);

            long costTimeEnd = System.currentTimeMillis();

            long totalTimeCost = costTimeEnd - costTimeStart;
            System.out.println("支付api请求总耗时：" + totalTimeCost + "ms");
        } catch (RpcException e) {
            long costTimeEnd = System.currentTimeMillis();
            long totalTimeCost = costTimeEnd - costTimeStart;
            System.out.println("支付api请求总耗时：" + totalTimeCost + "ms");
            e.printStackTrace();
        }





        System.out.println(GsonUtil.toJson(result));

        payService.close();
        ThreadPool.service.shutdown();

    }


}
