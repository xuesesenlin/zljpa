package com.zlj.zl.publics.login.controller;

import com.zlj.zl.publics.account.model.AccountModel;
import com.zlj.zl.publics.account.service.AccountService;
import com.zlj.zl.publics.login.model.LoginModel;
import com.zlj.zl.publics.token.model.TokenModel;
import com.zlj.zl.publics.token.service.TokenService;
import com.zlj.zl.sysConfig.shiro.token.MyUsernamePasswordToken;
import com.zlj.zl.util.base64.Base64Util;
import com.zlj.zl.util.resultJson.ResponseResult;
import com.zlj.zl.util.uuidUtil.GetUuid;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.SessionException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * login
 */
@Slf4j
@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private TokenService tokenService;

    @Autowired
    private AccountService accountService;

    /**
     * @param model         LoginModel
     * @param bindingResult BindingResult
     * @return ResponseResult<LoginModel>
     */
    @RequestMapping(value = "/login",
            method = RequestMethod.POST)
    public ResponseResult<LoginModel> login(
            @Valid @ModelAttribute("form") LoginModel model,
            BindingResult bindingResult,
            HttpServletRequest request,
            HttpServletResponse response) {

        ResponseResult<LoginModel> result = new ResponseResult<>();

//        验证码验证
//        boolean b = imgvrifyControllerDefaultKaptcha(request, response);
//        if (!b) {
//            result.setSuccess(false);
//            result.setMessage("验证码错误");
//            return result;
//        }

        if (bindingResult.hasErrors()) {
            result.setSuccess(false);
            result.setMessage(bindingResult.getFieldError().getDefaultMessage());
            return result;
        }

        ResponseResult<AccountModel> account = accountService.getByAccount(model.getUsername());
        if (account.isSuccess()) {
            if (account.getData().getFlag().equals("0")) {
                model.setTypes(account.getData().getTypes());
            } else if (account.getData().getFlag().equals("-1")) {
                result.setSuccess(false);
                result.setMessage("账户不存在");
                return result;
            } else {
                result.setSuccess(false);
                result.setMessage("账户已锁定");
                return result;
            }
        } else {
            result.setSuccess(false);
            result.setMessage("账户不存在");
            return result;
        }

        //验证用户和令牌的有效性
        MyUsernamePasswordToken token = new MyUsernamePasswordToken(model.getUsername(),
                Base64Util.encode(model.getPassword()),
                model.getTypes(),
                true);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            log.info("获取令牌成功");
//        生成新的token
            long times = System.currentTimeMillis() + (1000 * 60 * 15);
            TokenModel tokenModel = new TokenModel();
            tokenModel.setToken(GetUuid.getUUID());
            tokenModel.setIsUse("N");
            tokenModel.setEndTimes(times);
            tokenModel.setAccount(model.getUsername());
            ResponseResult<TokenModel> result1 = tokenService.add(tokenModel);
            if (result1.isSuccess()) {
//                用cookie方式
                Cookie cookie = new Cookie("token", tokenModel.getToken());
                cookie.setPath("/");
                cookie.setMaxAge(1000 * 60 * 15);
                response.addCookie(cookie);
                Cookie cookie2 = new Cookie("tokenu", Base64Util.encode(token.getUsername()));
                cookie2.setPath("/");
                cookie2.setMaxAge(1000 * 60 * 15);
                response.addCookie(cookie2);
                Cookie cookie3 = new Cookie("tokent", Base64Util.encode(model.getTypes()));
                cookie3.setPath("/");
                cookie3.setMaxAge(1000 * 60 * 15);
                response.addCookie(cookie3);
//                用头部信息方式
//                response.setHeader("Access-Control-Expose-Headers", "token");
//                response.setHeader("token", tokenModel.getToken());
                result.setSuccess(true);
                result.setMessage(tokenModel.getToken());
                return result;
            } else {
                result.setSuccess(false);
                result.setMessage("令牌生成错误");
                return result;
            }
        } catch (Exception e) {
            log.info(e.getMessage());
            result.setSuccess(false);
            result.setMessage("账号或密码错误");
            return result;
        }
    }

    @GetMapping("/logout")
    public ModelAndView logout(HttpServletRequest request,
                               HttpServletResponse response) {
        try {
//            cookie方式
            Cookie[] cookies = request.getCookies();
            for (int i = 0; i < cookies.length; i++) {
                if (cookies[i].getName().equals("token")) {
                    tokenService.updateToken(cookies[i].getValue());
                    Cookie cookie = new Cookie("token", null);
                    cookie.setPath("/");
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }
            }
//                    不缓存
            response.setDateHeader("Expires", -1);
            //为了保证兼容性
            response.setHeader("Cache-Control", "no-cache");
            response.setHeader("Pragma", "no-cache");

            Subject subject = SecurityUtils.getSubject();
            if (subject.isAuthenticated()) {
                subject.logout();
                if (log.isDebugEnabled()) {
                    log.debug("用户退出登录");
                }
            }
        } catch (SessionException e) {
            e.printStackTrace();
        }
        return new ModelAndView("redirect:/index");
    }

    @GetMapping("/logoutOK")
    public ModelAndView logoutOK() {
        return new ModelAndView("/logoutOK");
    }

    //    验证码验证
    private boolean imgvrifyControllerDefaultKaptcha(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        String captchaId = (String) httpServletRequest.getSession().getAttribute("vrifyCode");
        String parameter = httpServletRequest.getParameter("yzm");
        boolean b = captchaId.equals(parameter);
        return b;
    }
}
