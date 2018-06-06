package com.zlj.zl.sysConfig.accessControlFilter;

import com.zlj.zl.publics.token.model.TokenModel;
import com.zlj.zl.publics.token.service.TokenService;
import com.zlj.zl.publics.token.service.impl.TokenServiceImpl;
import com.zlj.zl.sysConfig.shiro.token.MyUsernamePasswordToken;
import com.zlj.zl.util.base64.Base64Util;
import com.zlj.zl.util.resultJson.ResponseResult;
import com.zlj.zl.util.uuidUtil.GetUuid;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author
 * @name 全局请求控制器
 */
@Slf4j
public class MyAccessControlFilter extends AccessControlFilter {

    //    单位毫秒
//    token 超时时间
    private int sessionOutTime = 1000 * 60 * 60 * 24;

    /**
     * 表示是否允许访问；mappedValue就是[urls]配置中拦截器参数部分，如果允许访问返回true，否则false；
     * (感觉这里应该是对白名单（不需要登录的接口）放行的)
     * 如果isAccessAllowed返回true则onAccessDenied方法不会继续执行
     * 这里可以用来判断一些不被通过的链接（个人备注）
     * * 表示是否允许访问 ，如果允许访问返回true，否则false；
     *
     * @param servletRequest
     * @param servletResponse
     * @param object          表示写在拦截器中括号里面的字符串 mappedValue 就是 [urls] 配置中拦截器参数部分
     * @return
     * @throws Exception
     */
    @Override
    public boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object object) throws Exception {
        Subject subject = getSubject(servletRequest, servletResponse);
        String url = getPathWithinApplication(servletRequest);
        log.info("当前用户正在访问的 url => " + url);
        log.info("subject.isPermitted(url);" + subject.isPermitted(url));
        return false;
    }

    /**
     * 表示当访问拒绝时是否已经处理了；如果返回true表示需要继续处理；如果返回false表示该拦截器实例已经处理了，将直接返回即可。
     * onAccessDenied是否执行取决于isAccessAllowed的值，如果返回true则onAccessDenied不会执行；如果返回false，执行onAccessDenied
     * 如果onAccessDenied也返回false，则直接返回，不会进入请求的方法（只有isAccessAllowed和onAccessDenied的情况下）
     */
    @Override
    public boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        TokenService tokenService = new TokenServiceImpl();

//        cookie方式
        String token_str = "";
        String token_user = "";
        String token_types = "";
        Cookie[] cookies = httpServletRequest.getCookies();
        if (cookies == null || cookies.length <= 0) {
            onLoginFail(httpServletRequest, httpServletResponse, "非法的请求");
            return false;
        }
        for (int i = 0; i < cookies.length; i++) {
            if (cookies[i].getName().equals("token")) {
                token_str = cookies[i].getValue();
            } else if (cookies[i].getName().equals("tokenu")) {
                token_user = cookies[i].getValue();
            } else if (cookies[i].getName().equals("tokent")) {
                token_types = cookies[i].getValue();
            }
        }
        if ((token_str == null || token_str.trim().equals(""))
                || (token_user == null || token_user.trim().equals(""))
                || (token_types == null || token_types.trim().equals(""))) {
            log.info("未获取cookie信息");
            onLoginFail(httpServletRequest, httpServletResponse, "非法的请求");
            return false;
        }

//验证用户和令牌的有效性(此处应该根据uuid取缓存数据然后判断令牌时候有效)
        MyUsernamePasswordToken token = new MyUsernamePasswordToken(Base64Util.decode(token_user),
                token_str,
                Base64Util.decode(token_types),
                false);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            log.info("令牌验证成功");
            //        废弃原有令牌
            ResponseResult<TokenModel> result1 = tokenService.updateToken2(token_str);
            if (result1.isSuccess()) {
//        新的token
//        保存进库
                TokenModel tokenModel = new TokenModel();
                tokenModel.setEndTimes(System.currentTimeMillis() + sessionOutTime);
                tokenModel.setIsUse("N");
                tokenModel.setToken(GetUuid.getUUID());
                tokenModel.setAccount(token.getUsername());
                ResponseResult<TokenModel> result = tokenService.add2(tokenModel);
                if (result.isSuccess()) {
//                头部方式
//                HttpServletResponse httpServletResponse = (HttpServletResponse) response;
//                httpServletResponse.setHeader("Access-Control-Expose-Headers", "token");
//                httpServletResponse.setHeader("token", tokenModel.getToken());
//                cookie方式
                    for (int i = 0; i < cookies.length; i++) {
                        if (cookies[i].getName().equals("token")) {
                            tokenService.updateToken2(cookies[i].getValue());
                            Cookie cookie = new Cookie("token", tokenModel.getToken());
                            cookie.setPath("/");
                            cookie.setMaxAge(sessionOutTime);
                            httpServletResponse.addCookie(cookie);
                        }
                    }
                    return true;
                } else
                    return false;
            } else {
                return false;
            }
        } catch (Exception e) {
            log.info("令牌验证失败，令牌错误");
            log.info(e.getMessage());
            onLoginFail(httpServletRequest, (HttpServletResponse) response, e.getMessage());
            return false;
        }
    }

    /**
     * 登录失败
     */
    private void onLoginFail(HttpServletRequest request, HttpServletResponse response, String message) throws IOException {
        log.info("设置返回");
        response.setHeader("Content-type", "application/json; charset=utf-8");
        response.setHeader("Access-Control-Expose-Headers", "token");
        response.setHeader("token", "logout");
//        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

        String requestType = request.getHeader("X-Requested-With");
        if ("XMLHttpRequest".equals(requestType)) {
//            ResponseResult result = new ResponseResult();
//            result.setSuccess(false);
//            result.setMessage(message);
//            ObjectMapper objectMapper = new ObjectMapper();
            //对象转JSON
//            String json = objectMapper.writeValueAsString(result);//返回字符串，输出
//            response.getWriter().append(json);
            response.setContentType("text/html; charset=UTF-8");
            String host = request.getHeader("host");
            String s = "<script>alert('" + message + "');window.location.href='http://" + host + "/index'</script>";
            PrintWriter writer = response.getWriter();
            writer.print(s);
            writer.close();
        } else {
//            非ajax请求
            response.sendRedirect("/index");
        }
    }

    /**
     * TODO 跨域请求
     */
    private void dealCrossDomain() {

    }
}
