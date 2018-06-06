package com.zlj.zl.sysConfig.shiro.realm;

import com.zlj.zl.sysConfig.shiro.token.MyUsernamePasswordToken;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.realm.Realm;

import java.util.Collection;

/**
 * @author Created by pangkunkun on 2017/11/22.
 * 自定义Authenticator
 * 注意，当需要分别定义处理普通用户和管理员验证的Realm时，对应Realm的全类名应该包含字符串“User”，或者“Admin”。
 * 并且，他们不能相互包含，例如，处理普通用户验证的Realm的全类名中不应该包含字符串"Admin"。
 */
public class MyModularRealmAuthenticator extends ModularRealmAuthenticator {

    @Override
    protected AuthenticationInfo doAuthenticate(AuthenticationToken authenticationToken)
            throws AuthenticationException {

        // 判断getRealms()是否返回为空
        assertRealmsConfigured();
        MyUsernamePasswordToken token = (MyUsernamePasswordToken) authenticationToken;
        // 登录类型
        String loginType = token.getType();
        // 所有Realm
        Collection<Realm> realms = getRealms();
        for (Realm realm : realms) {
            if (realm.getName().equals(loginType)) {
//                指定的realm进行验证
                return doSingleRealmAuthentication(realm, token);
            }
        }
//        当未找到制定类型时
//        return doMultiRealmAuthentication(null, token);
        throw new UnauthorizedException("权限不足");
    }
}