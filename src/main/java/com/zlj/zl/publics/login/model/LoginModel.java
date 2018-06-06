package com.zlj.zl.publics.login.model;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class LoginModel implements Serializable {

    @NotBlank(message = "账户不能为空")
    @Size(min = 6, max = 12, message = "账户长度为6-12位之间")
    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "账户不能包含特殊字符")
    private String username;

    @NotBlank(message = "密码不能为空")
    @Size(min = 6, max = 20, message = "密码长度为6-20位之间")
    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "密码不能包含特殊字符")
    private String password;

    private String yzm;
    //    shiro realm验证类型
    private String types;

    public LoginModel() {
        super();
    }

    public LoginModel(String username, String password, String yzm, String types) {
        this.username = username;
        this.password = password;
        this.yzm = yzm;
        this.types = types;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getYzm() {
        return yzm;
    }

    public void setYzm(String yzm) {
        this.yzm = yzm;
    }

    @Override
    public String toString() {
        return "LoginModel{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", yzm='" + yzm + '\'' +
                '}';
    }
}
