package com.cloudwing.checkstand.consumer.platform;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cloudwing.checkstand.consumer.platform.beans.Company;
import com.cloudwing.checkstand.consumer.platform.beans.CompanyDetail;
import com.cloudwing.checkstand.consumer.platform.beans.CompanyList;
import com.cloudwing.checkstand.consumer.platform.beans.UserDetail;
import com.yunyitg.rpc.base.exception.RpcException;
import com.yunyitg.rpc.common.ThreadPool;
import com.yunyitg.rpc.common.YunyiRpcClientFactory;
import com.yunyitg.rpc.util.GsonUtil;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public class PlatformConsumerTest {

    static String token1 = "af31ad83H1YWEHTEZER11KbgwARgoBSUEWXBYUW1YAa1kGEgwGSkFLVEJEDV5aUVwTX1dIElEVEzpKARFBag5RSUAKAkkYQEYBAQdUAQQICAFVGkdO";
    
    public static void main(String[] args) {
        //线上测试环境
        String host = "rpc.cw.com";
        int port = 9501;
//        String name = "test";
        String name = "Platform";
//        String token = "6QLuwr4QGZg0Gsq36LgphG8yIwzPHKBw";
        String token = "vJrieIEUhCtRymONXbFpXIdasSaXd6c8";
        long timeoutms = 5*1000L;

        //建立工厂类
        YunyiRpcClientFactory factory = new YunyiRpcClientFactory(host, port, name, token, timeoutms);

        final UserService userService = new UserServiceImpl(factory);
        final CompanyService companyService = new CompanyServiceImpl(factory);

        String infoJson = null;

        try {
//            infoJson = userService.authToken(token1);
//            if (!"false".equals(infoJson)) {
//                JSONObject jsonObject = JSONObject.parseObject(infoJson);
//                String userId = jsonObject.getString("user_id");
//                String serviceId = jsonObject.getString("service_id");
//                String sessionId = jsonObject.getString("sessionid");
//                String appSessKey = jsonObject.getString("app_sess_key");
//                System.out.println("userId:" +userId + " ,serviceId:" +serviceId + " .sessionId:" + sessionId + " ,appSessKey:" + appSessKey);
//
//
//
//            }
            UserDetail userDetail = userService.getUserInfo(49);
            System.out.println(GsonUtil.toJson(userDetail));




//            CompanyList companyList = companyService.getCompanyList(1,50);
//            for (Company c : companyList.getCompanies()) {
//                System.out.println(GsonUtil.toJson(c));
//            }
//
//            CompanyDetail companyDetail = companyService.getCompanyInfo(20);
//            System.out.println(GsonUtil.toJson(companyDetail));
            
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (RpcException e) {
            e.printStackTrace();
        }
        
        System.out.println(infoJson);

        userService.close();
        companyService.close();
        ThreadPool.service.shutdown();
    }
}
