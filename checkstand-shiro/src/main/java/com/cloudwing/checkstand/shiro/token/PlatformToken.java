package com.cloudwing.checkstand.shiro.token;

import org.apache.shiro.authc.UsernamePasswordToken;

import java.io.Serializable;

public class PlatformToken extends UsernamePasswordToken implements Serializable {

    private static final long serialVersionUID = 1L;

    public PlatformToken(String token, String appSessKey) {
        super(token, appSessKey);
        this.pswd = appSessKey;
    }

    /** 登录的IP地址 */
    private String ip;

    private String pswd;

    public String getPswd() {
        return pswd;
    }

    public void setPswd(String pswd) {
        this.pswd = pswd;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
