package com.cloudwing.checkstand.consumer.pay.config;

import com.cloudwing.checkstand.consumer.pay.PayService;
import com.cloudwing.checkstand.consumer.pay.PayServiceImpl;
import com.yunyitg.rpc.common.YunyiRpcClientFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ConfigurationProperties(prefix = "rpc")
@PropertySource(value = "consumer.properties")
public class RpcConfig {

    private String host;

    private int port;

    private String user;

    private String token;

    private int timeoutms;


    @Bean
    public PayService payService() {
        PayService payService = new PayServiceImpl(yunyiRpcClientFactory());
        return payService;
    }

    @Bean
    public YunyiRpcClientFactory yunyiRpcClientFactory() {

        YunyiRpcClientFactory factory = new YunyiRpcClientFactory(host, port, user, token, timeoutms);

        return factory;
    }



    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getTimeoutms() {
        return timeoutms;
    }

    public void setTimeoutms(int timeoutms) {
        this.timeoutms = timeoutms;
    }

}
