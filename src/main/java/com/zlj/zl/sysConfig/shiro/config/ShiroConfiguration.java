package com.zlj.zl.sysConfig.shiro.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.zlj.zl.sysConfig.accessControlFilter.MyAccessControlFilter;
import com.zlj.zl.sysConfig.shiro.realm.AdminShiroRealm;
import com.zlj.zl.sysConfig.shiro.realm.MyModularRealmAuthenticator;
import com.zlj.zl.sysConfig.shiro.realm.SuperAdminShiroRealm;
import com.zlj.zl.sysConfig.shiro.realm.UserShiroRealm;
import org.apache.shiro.authc.pam.AtLeastOneSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * http://blog.csdn.net/v2sking/article/details/71941530
 */
@Configuration
public class ShiroConfiguration {

    @Bean
    public static LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }

    @Bean
    @ConditionalOnMissingBean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAAP = new DefaultAdvisorAutoProxyCreator();
        defaultAAP.setProxyTargetClass(true);
        defaultAAP.setUsePrefix(true);
        return defaultAAP;
    }

    /**
     * ShiroFilterFactoryBean 处理拦截资源文件问题。
     * 注意：单独一个ShiroFilterFactoryBean配置是或报错的，因为在
     * 初始化ShiroFilterFactoryBean的时候需要注入：SecurityManager
     * <p>
     * Filter Chain定义说明
     * 1、一个URL可以配置多个Filter，使用逗号分隔
     * 2、当设置多个过滤器时，全部验证通过，才视为通过
     * 3、部分过滤器可指定参数，如perms，roles
     */
    @Bean
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        // 必须设置 SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        shiroFilterFactoryBean.setLoginUrl("/index");
        // 登录成功后要跳转的链接
        shiroFilterFactoryBean.setSuccessUrl("/home/index");
        //未授权界面;
        shiroFilterFactoryBean.setUnauthorizedUrl("/error/403");

        //自定义拦截器
        Map<String, Filter> filtersMap = new LinkedHashMap<>();
        filtersMap.put("myAccessControlFilter", new MyAccessControlFilter());
        shiroFilterFactoryBean.setFilters(filtersMap);

        //拦截器.
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();

        //我做的是无状态的，这里的东西实际上是用不到的，仅供参考
        //配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了
//        filterChainDefinitionMap.put("/login/logout", "logout");

        filterChainDefinitionMap.put("/Angular/**", "anon");
        filterChainDefinitionMap.put("/css/**", "anon");
        filterChainDefinitionMap.put("/email_templates/**", "anon");
        filterChainDefinitionMap.put("/font-awesome/**", "anon");
        filterChainDefinitionMap.put("/fonts/**", "anon");
        filterChainDefinitionMap.put("/img/**", "anon");
        filterChainDefinitionMap.put("**.js", "anon");
        filterChainDefinitionMap.put("/style/**", "anon");
        filterChainDefinitionMap.put("/date/**", "anon");
        filterChainDefinitionMap.put("/ckeditor/**", "anon");
        filterChainDefinitionMap.put("/defaultKaptcha", "anon");
        filterChainDefinitionMap.put("/**/skin-config**", "anon");
        filterChainDefinitionMap.put("/toastr.js.map", "anon");
        filterChainDefinitionMap.put("/", "anon");
        filterChainDefinitionMap.put("/index", "anon");
        filterChainDefinitionMap.put("/favicon.ico", "anon");
        filterChainDefinitionMap.put("/login/**", "anon");
        filterChainDefinitionMap.put("/weather/**", "anon");
        filterChainDefinitionMap.put("/laydate/**", "anon");
        filterChainDefinitionMap.put("/**/exp**/**", "anon");
////健康状态相关
////        这个路径主要是用来统计系统的状况，默认里面目前只有系统状况和磁盘状况
        filterChainDefinitionMap.put("/health", "anon");
////        里面包含了目前的环境变量，包括application.yaml配置的属性，以及systemProperties都能够拿到
////        filterChainDefinitionMap.put("/env", "anon");
////        里面包含了目前线程的一个快照信息
//        filterChainDefinitionMap.put("/dump", "anon");
////        里面包含了Controller的所有mapping信息，开发中新手经常会遇到访问不到controller的情况，可以根据这个查看是否被扫描
//        filterChainDefinitionMap.put("/mappings", "anon");
////        trace目前主要是监控http请求的,监控每个请求的状况
//        filterChainDefinitionMap.put("/trace", "anon");
////        是整个监控里面的核心信息
//        filterChainDefinitionMap.put("/metrics", "anon");

        filterChainDefinitionMap.put("/**", "myAccessControlFilter");
        //<!-- 过滤链定义，从上向下顺序执行，一般将 /**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
        //<!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
        //自定义加载权限资源关系
//        List<Resources> resourcesList = resourcesService.queryAll();
//        for(Resources resources:resourcesList){
//            if (StringUtil.isNotEmpty(resources.getResurl())) {
//                String permission = "perms[" + resources.getResurl()+ "]";
//                filterChainDefinitionMap.put(resources.getResurl(),permission);
//            }
//        }
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setAuthenticator(customizedModularRealmAuthenticator());
        List<Realm> realms = new ArrayList<>();
        realms.add(superAdminShiroRealm());
        realms.add(adminShiroRealm());
        realms.add(userShiroRealm());
        securityManager.setRealms(realms);
        return securityManager;
    }

    @Bean
    public SuperAdminShiroRealm superAdminShiroRealm() {
        SuperAdminShiroRealm myShiroRealm = new SuperAdminShiroRealm();
        //加密判断
//        myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return myShiroRealm;
    }

    @Bean
    public AdminShiroRealm adminShiroRealm() {
        AdminShiroRealm myShiroRealm = new AdminShiroRealm();
        return myShiroRealm;
    }

    @Bean
    public UserShiroRealm userShiroRealm() {
        UserShiroRealm myShiroRealm = new UserShiroRealm();
        return myShiroRealm;
    }


    /**
     * 开启shiro aop注解支持.
     * 使用代理方式;所以需要开启代码支持;
     *
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    /**
     * 自定义的Realm管理，主要针对多realm
     */
    @Bean
    public MyModularRealmAuthenticator customizedModularRealmAuthenticator() {
        MyModularRealmAuthenticator customizedModularRealmAuthenticator = new MyModularRealmAuthenticator();
        //设置realm判断条件
        customizedModularRealmAuthenticator.setAuthenticationStrategy(new AtLeastOneSuccessfulStrategy());
        return customizedModularRealmAuthenticator;
    }

    /**
     * 系统自带的Realm管理，主要针对多realm
     */
    @Bean
    public ModularRealmAuthenticator modularRealmAuthenticator() {
        ModularRealmAuthenticator modularRealmAuthenticator = new ModularRealmAuthenticator();
        modularRealmAuthenticator.setAuthenticationStrategy(new AtLeastOneSuccessfulStrategy());
        return modularRealmAuthenticator;
    }

}
