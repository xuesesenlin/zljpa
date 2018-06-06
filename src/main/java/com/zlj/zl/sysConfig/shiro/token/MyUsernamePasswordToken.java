package com.zlj.zl.sysConfig.shiro.token;

import org.apache.shiro.authc.UsernamePasswordToken;

import java.util.Map;

/**
 * @author Created by pangkunkun on 2017/11/20.
 */
public class MyUsernamePasswordToken extends UsernamePasswordToken {

    private String username;
    private Map<String, ?> params;
    private String signature;
    private String type;
    private boolean isLogin;

    public MyUsernamePasswordToken(String username,
                                   String signature,
                                   String type,
                                   boolean isLogin) {
        this.username = username;
        this.signature = signature;
        this.type = type;
        this.isLogin = isLogin;
    }

    public MyUsernamePasswordToken(String username, Map<String, ?> params, String signature) {
        this.username = username;
        this.params = params;
        this.signature = signature;
    }

    public MyUsernamePasswordToken(String username, String type, String signature) {
        this.username = username;
        this.type = type;
        this.signature = signature;
    }

    public MyUsernamePasswordToken(String username, String signature) {
        this.username = username;
        this.signature = signature;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    public Map<String, ?> getParams() {
        return params;
    }

    public void setParams(Map<String, ?> params) {
        this.params = params;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }

    @Override
    public Object getPrincipal() {
        return username;
    }

    @Override
    public Object getCredentials() {
        return signature;
    }

    @Override
    public String toString() {
        return "MyUsernamePasswordToken{" +
                "username='" + username + '\'' +
                ", params=" + params +
                ", signature='" + signature + '\'' +
                ", type='" + type + '\'' +
                ", isLogin=" + isLogin +
                '}';
    }
}
