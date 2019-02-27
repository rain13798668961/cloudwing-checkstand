package com.cloudwing.checkstand.consumer.platform;

import com.cloudwing.checkstand.consumer.platform.beans.Session;
import com.cloudwing.checkstand.consumer.platform.beans.UserDetail;
import com.cloudwing.checkstand.consumer.platform.beans.UserList;
import com.yunyitg.rpc.base.exception.RpcException;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

/**
 * 用户信息服务-服务接口
 * @author 云翼RPC-java-代码自动生成
 *
 */
public interface UserService {

    /**
     * 校验应用token信息合法性
     * @param token
     * @return  用户基础身份信息
     * @throws InterruptedException, ExecutionException, TimeoutException, RpcException
     */
    String authToken(String token) throws InterruptedException, ExecutionException, TimeoutException, RpcException;

    /**
     * 获取用户列表
     * @param company_id 查询的公司id
     * @param page 查询页数
     * @param limit 页数限制
     * @return  列表
     * @throws InterruptedException, ExecutionException, TimeoutException, RpcException
     */
    UserList getUserList(Integer company_id, Integer page, Integer limit) throws InterruptedException, ExecutionException, TimeoutException, RpcException;

    /**
     * 获取用户详情
     * @param id 用户id
     * @return  用户详情
     * @throws InterruptedException, ExecutionException, TimeoutException, RpcException
     */
    UserDetail getUserInfo(Integer id) throws InterruptedException, ExecutionException, TimeoutException, RpcException;

    /**
     * 获取单个sessionid状态
     * @param sessionId 用户sessionid
     * @return  对应的用户id
     * @throws InterruptedException, ExecutionException, TimeoutException, RpcException
     */
    Integer authSessionId(String sessionId) throws InterruptedException, ExecutionException, TimeoutException, RpcException;

    /**
     * 获取若干个sessionid状态
     * @param sessionIds
     * @return
     * @throws InterruptedException, ExecutionException, TimeoutException, RpcException
     */
    List<Session> authSessionIds(List<String> sessionIds) throws InterruptedException, ExecutionException, TimeoutException, RpcException;

    /**
     * 关闭服务
     */
    public void close();

    /**
     * 连接是否有效
     */
    public boolean isActive();

}