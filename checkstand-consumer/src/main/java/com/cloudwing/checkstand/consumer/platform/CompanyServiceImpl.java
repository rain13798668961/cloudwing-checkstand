package com.cloudwing.checkstand.consumer.platform;

import com.cloudwing.checkstand.consumer.platform.beans.CompanyDetail;
import com.cloudwing.checkstand.consumer.platform.beans.CompanyList;
import com.google.gson.reflect.TypeToken;
import com.yunyitg.rpc.base.YunyiTextMsg;
import com.yunyitg.rpc.base.exception.RpcException;
import com.yunyitg.rpc.common.YunyiRpcClientFactory;
import com.yunyitg.rpc.consumer.YunyiRpcClient;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

/**
 * 公司信息服务-服务接口-实现类
 * @author 云翼RPC-java-代码自动生成
 *
 */
public class CompanyServiceImpl implements CompanyService {

    private YunyiRpcClient yunyiRpcClient;
    private YunyiRpcClientFactory yunyiRpcClientFactory;

    public CompanyServiceImpl() {}

    public CompanyServiceImpl(YunyiRpcClientFactory yunyiRpcClientFactory) {
        this.yunyiRpcClientFactory = yunyiRpcClientFactory;
    }

    public void setYunyiRpcClientFactory(YunyiRpcClientFactory yunyiRpcClientFactory) {
        this.yunyiRpcClientFactory = yunyiRpcClientFactory;
    }

    public synchronized YunyiRpcClient getYunyiRpcClient() throws InterruptedException, ExecutionException, TimeoutException, RpcException {
        if(yunyiRpcClient == null){
            yunyiRpcClient = yunyiRpcClientFactory.createRpcClient();
        }
        return yunyiRpcClient;
    }

    /**
     * 获取公司列表
     * @param page 页数
     * @param limit 页限制
     * @return
     * @throws InterruptedException, ExecutionException, TimeoutException, RpcException
     */
    @Override
    public CompanyList getCompanyList(Integer page, Integer limit) throws InterruptedException, ExecutionException, TimeoutException, RpcException{
        YunyiTextMsg<CompanyList> result = getYunyiRpcClient().invoke(new TypeToken<YunyiTextMsg<CompanyList>>(){}.getType(), "Platform.Company.GetCompanyList", page, limit);
        if (result.isSuccess()) {
            return result.getResponse();
        }else {
            throw new RpcException(result.getMsg());
        }
    }

    /**
     * 获取公司详情
     * @param id 公司id
     * @return
     * @throws InterruptedException, ExecutionException, TimeoutException, RpcException
     */
    @Override
    public CompanyDetail getCompanyInfo(Integer id) throws InterruptedException, ExecutionException, TimeoutException, RpcException{
        YunyiTextMsg<CompanyDetail> result = getYunyiRpcClient().invoke(new TypeToken<YunyiTextMsg<CompanyDetail>>(){}.getType(), "Platform.Company.GetCompanyInfo", id);
        if (result.isSuccess()) {
            return result.getResponse();
        }else {
            throw new RpcException(result.getMsg());
        }
    }

    /**
     * 关闭服务
     */
    @Override
    public void close(){
        if(yunyiRpcClient != null){
            yunyiRpcClient.close();
        }
    }

    /**
     * 连接是否有效
     */
    @Override
    public boolean isActive(){
        return yunyiRpcClient.isActive();
    }

}