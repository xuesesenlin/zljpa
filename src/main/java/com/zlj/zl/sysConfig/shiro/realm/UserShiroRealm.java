package com.zlj.zl.sysConfig.shiro.realm;

import com.zlj.zl.admin.role.service.RoleAccService;
import com.zlj.zl.admin.role.service.RoleDetailedService;
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
 * 用于判断user
 */
@Slf4j
@Configuration
public class UserShiroRealm extends AuthorizingRealm {

    @Autowired
    private AccountService accountService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private RoleAccService roleAccService;
    @Autowired
    private RoleDetailedService roleDetailedService;

    @Override
    public String getName() {
        return "user";
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
                } else {
//                    token未找到
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
//        权限
//        Object primaryPrincipal = principalCollection.getPrimaryPrincipal();
//        try {
//            TokenModel model = (TokenModel) primaryPrincipal;
//            ResponseResult<AccountModel> accounts = accountService.getByAccount(model.getAccount());
//            if (accounts.isSuccess()) {
//                ResponseResult<List<RoleAccModel>> roleAccs = roleAccService.findByAccountId(accounts.getData().getUuid());
//                if (roleAccs.isSuccess()) {
//                    ResponseResult<List<JurisdictionModel>> result = roleDetailedService.findByRoleId2(roleAccs.getData().get(0).getRoleId());
//                    if (result.isSuccess()) {
//                        result.getData().forEach(k -> {
//                            if (k != null && k.getIdentification() != null) {
//                                info.addStringPermission(k.getIdentification());
//                                info.addRole(k.getIdentification().split(":")[0]);
//                            }
//                        });
//                    }
//                }
//            }
//        } catch (ClassCastException e) {
//            try {
//                AccountModel model = (AccountModel) primaryPrincipal;
//                ResponseResult<AccountModel> accounts = accountService.getByAccount(model.getAccount());
//                if (accounts.isSuccess()) {
//                    ResponseResult<List<RoleAccModel>> roleAccs = roleAccService.findByAccountId(accounts.getData().getUuid());
//                    if (roleAccs.isSuccess()) {
//                        ResponseResult<List<JurisdictionModel>> result = roleDetailedService.findByRoleId2(roleAccs.getData().get(0).getRoleId());
//                        if (result.isSuccess()) {
//                            result.getData().forEach(k -> {
//                                if (k != null && k.getIdentification() != null) {
//                                    info.addStringPermission(k.getIdentification());
//                                    info.addRole(k.getIdentification().split(":")[0]);
//                                }
//                            });
//                        }
//                    }
//                }
//            } catch (Exception e1) {
//                return info;
//            }
//            return info;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        return info;
    }

}