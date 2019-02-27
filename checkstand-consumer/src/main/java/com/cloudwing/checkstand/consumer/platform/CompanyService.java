package com.cloudwing.checkstand.consumer.platform;

import com.cloudwing.checkstand.consumer.platform.beans.CompanyDetail;
import com.cloudwing.checkstand.consumer.platform.beans.CompanyList;
import com.yunyitg.rpc.base.exception.RpcException;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

/**
 * 公司信息服务-服务接口
 * @author 云翼RPC-java-代码自动生成
 *
 */
public interface CompanyService {

    /**
     * 获取公司列表
     * @param page 页数
     * @param limit 页限制
     * @return
     * @throws InterruptedException, ExecutionException, TimeoutException, RpcException
     */
    CompanyList getCompanyList(Integer page, Integer limit) throws InterruptedException, ExecutionException, TimeoutException, RpcException;

    /**
     * 获取公司详情
     * @param id 公司id
     * @return
     * @throws InterruptedException, ExecutionException, TimeoutException, RpcException
     */
    CompanyDetail getCompanyInfo(Integer id) throws InterruptedException, ExecutionException, TimeoutException, RpcException;

    /**
     * 关闭服务
     */
    public void close();

    /**
     * 连接是否有效
     */
    public boolean isActive();

}