package com.cloudwing.checkstand.company.service;

import com.cloudwing.checkstand.company.entity.Company;
import com.yunyitg.rpc.base.exception.RpcException;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public interface CompanyService {

    /**
     * 根据外贸圈平台企业id查找企业数据
     * （先在本应用查询，没有再通过平台RPC接口查找）
     * @param platformCompanyId
     * @return
     * @throws InterruptedException
     * @throws ExecutionException
     * @throws TimeoutException
     * @throws RpcException
     */
    Company lookupByPlatformCompanyId(String platformCompanyId) throws InterruptedException,ExecutionException,TimeoutException,RpcException;

}
