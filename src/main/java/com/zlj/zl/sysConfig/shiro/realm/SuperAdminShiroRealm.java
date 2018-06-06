package com.zlj.zl.sysConfig.shiro.realm;

import com.zlj.zl.publics.account.model.AccountModel;
import com.zlj.zl.publics.account.service.AccountService;
import com.zlj.zl.publics.token.model.TokenModel;
import com.zlj.zl.publics.token.service.TokenService;
import com.zlj.zl.sysConfig.shiro.token.MyUsernamePasswordToken;
import com.zlj.zl.util.resultJson.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

/**
 * 用于判断admin
 */
@Slf4j
@Configuration
public class SuperAdminShiroRealm extends AuthorizingRealm {

    @Autowired
    private AccountService accountService;
    @Autowired
    private TokenService tokenService;

    @Override
    public String getName() {
        return "superAdmin";
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        MyUsernamePasswordToken myToken = (MyUsernamePasswordToken) token;
        if (myToken.getSignature() == null || myToken.getSignature().isEmpty()) {
            //请从新登录;
            log.info("令牌为空");
            throw new UnknownAccountException("账号或密码错误!");
        }
//        账号密码登录
        if (myToken.isLogin()) {
            ResponseResult<AccountModel> result = accountService.getByAccount(myToken.getUsername());
            if (result.isSuccess()) {
                return new SimpleAuthenticationInfo(
                        result.getData(),
                        result.getData().getPassword(),
                        getName()
                );
            } else
                throw new UnknownAccountException("账号或密码错误!");
        } else {
            //令牌登录
            ResponseResult<AccountModel> result = accountService.getByAccount(myToken.getUsername());
            if (result.isSuccess()) {
                ResponseResult<TokenModel> result1 = tokenService.getByToken(myToken.getSignature());
                if (result1.isSuccess()) {
                    if (result1.getData().getIsUse().equals("N")) {
                        if (result1.getData().getEndTimes() > System.currentTimeMillis()) {
                            return new SimpleAuthenticationInfo(
                                    result1.getData(),
                                    result1.getData().getToken(),
                                    getName());
                        } else
                            throw new UnknownAccountException("登录超时!");
                    } else
                        throw new UnknownAccountException("登录超时!");
                }
                return new SimpleAuthenticationInfo(
                        result.getData(),
                        result.getData().getPassword(),
                        getName());
            } else
                throw new UnknownAccountException("账号或密码错误!");
        }
    }

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        Set<String> names = principalCollection.getRealmNames();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        names.forEach(k -> {
//            系统角色名称(系统角色对应realm，非数据设计的角色体系)
            info.addRole(k);
        });
        return info;
    }

}