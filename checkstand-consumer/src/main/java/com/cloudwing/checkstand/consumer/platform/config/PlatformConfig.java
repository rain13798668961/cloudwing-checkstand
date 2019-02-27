package com.cloudwing.checkstand.consumer.platform.config;

import com.cloudwing.checkstand.consumer.platform.CompanyService;
import com.cloudwing.checkstand.consumer.platform.CompanyServiceImpl;
import com.cloudwing.checkstand.consumer.platform.UserService;
import com.cloudwing.checkstand.consumer.platform.UserServiceImpl;
import com.yunyitg.rpc.common.YunyiRpcClientFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ConfigurationProperties(prefix = "rpc")
@PropertySource(value = "consumer.properties")
public class PlatformConfig {

    private String host;

    private int port;

    private String user;

    private String token;

    private int timeoutms;

    @Bean
    public UserService userService() {
        UserService userService = new UserServiceImpl(yunyiRpcClientPlatformFactory());
        return userService;
    }

    @Bean
    public CompanyService companyService() {
        CompanyService companyService = new CompanyServiceImpl(yunyiRpcClientPlatformFactory());
        return companyService;
    }

    @Bean
    public YunyiRpcClientFactory yunyiRpcClientPlatformFactory() {

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
