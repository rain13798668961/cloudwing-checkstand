package com.cloudwing.checkstand.consumer.platform;

import com.cloudwing.checkstand.consumer.platform.beans.Session;
import com.cloudwing.checkstand.consumer.platform.beans.UserDetail;
import com.cloudwing.checkstand.consumer.platform.beans.UserList;
import com.google.gson.reflect.TypeToken;
import com.yunyitg.rpc.base.YunyiTextMsg;
import com.yunyitg.rpc.base.exception.RpcException;
import com.yunyitg.rpc.common.YunyiRpcClientFactory;
import com.yunyitg.rpc.consumer.YunyiRpcClient;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

/**
 * 用户信息服务-服务接口-实现类
 * @author 云翼RPC-java-代码自动生成
 *
 */
public class UserServiceImpl implements UserService {

    private YunyiRpcClient yunyiRpcClient;
    private YunyiRpcClientFactory yunyiRpcClientFactory;

    public UserServiceImpl() {}

    public UserServiceImpl(YunyiRpcClientFactory yunyiRpcClientFactory) {
        this.yunyiRpcClientFactory = yunyiRpcClientFactory;
    }

    public void setYunyiRpcClientFactory(YunyiRpcClientFactory yunyiRpcClientFactory) {
        this.yunyiRpcClientFactory = yunyiRpcClientFactory;
    }

    public synchronized YunyiRpcClient getYunyiRpcClient() throws InterruptedException, ExecutionException, TimeoutException, RpcException {
        if(yunyiRpcClient == null || !yunyiRpcClient.isActive()){
            yunyiRpcClient = yunyiRpcClientFactory.createRpcClient();
        }
        return yunyiRpcClient;
    }

    /**
     * 校验应用token信息合法性
     * @param token
     * @return  用户基础身份信息
     * @throws InterruptedException, ExecutionException, TimeoutException, RpcException
     */
    @Override
    public String authToken(String token) throws InterruptedException, ExecutionException, TimeoutException, RpcException{
        System.out.println("netty连接状态：" + getYunyiRpcClient().isActive());
        YunyiTextMsg<String> result = getYunyiRpcClient().invoke(new TypeToken<YunyiTextMsg<String>>(){}.getType(), "Platform.User.AuthToken", token);
        if (result.isSuccess()) {
            return result.getResponse();
        }else {
            throw new RpcException(result.getMsg());
        }
    }

    /**
     * 获取用户列表
     * @param company_id 查询的公司id
     * @param page 查询页数
     * @param limit 页数限制
     * @return  列表
     * @throws InterruptedException, ExecutionException, TimeoutException, RpcException
     */
    @Override
    public UserList getUserList(Integer company_id, Integer page, Integer limit) throws InterruptedException, ExecutionException, TimeoutException, RpcException{
        YunyiTextMsg<UserList> result = getYunyiRpcClient().invoke(new TypeToken<YunyiTextMsg<UserList>>(){}.getType(), "Platform.User.GetUserList", company_id, page, limit);
        if (result.isSuccess()) {
            return result.getResponse();
        }else {
            throw new RpcException(result.getMsg());
        }
    }

    /**
     * 获取用户详情
     * @param id 用户id
     * @return  用户详情
     * @throws InterruptedException, ExecutionException, TimeoutException, RpcException
     */
    @Override
    public UserDetail getUserInfo(Integer id) throws InterruptedException, ExecutionException, TimeoutException, RpcException{
        YunyiTextMsg<UserDetail> result = getYunyiRpcClient().invoke(new TypeToken<YunyiTextMsg<UserDetail>>(){}.getType(), "Platform.User.GetUserInfo", id);
        if (result.isSuccess()) {
            return result.getResponse();
        }else {
            throw new RpcException(result.getMsg());
        }
    }

    /**
     * 获取单个sessionid状态
     * @param sessionId 用户sessionid
     * @return  对应的用户id
     * @throws InterruptedException, ExecutionException, TimeoutException, RpcException
     */
    @Override
    public Integer authSessionId(String sessionId) throws InterruptedException, ExecutionException, TimeoutException, RpcException{
        YunyiTextMsg<Integer> result = getYunyiRpcClient().invoke(new TypeToken<YunyiTextMsg<Integer>>(){}.getType(), "Platform.User.AuthSessionId", sessionId);
        if (result.isSuccess()) {
            return result.getResponse();
        }else {
            throw new RpcException(result.getMsg());
        }
    }

    /**
     * 获取若干个sessionid状态
     * @param sessionIds
     * @return
     * @throws InterruptedException, ExecutionException, TimeoutException, RpcException
     */
    @Override
    public List<Session> authSessionIds(List<String> sessionIds) throws InterruptedException, ExecutionException, TimeoutException, RpcException{
        YunyiTextMsg<List<Session>> result = getYunyiRpcClient().invoke(new TypeToken<YunyiTextMsg<List<Session>>>(){}.getType(), "Platform.User.AuthSessionIds", sessionIds);
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