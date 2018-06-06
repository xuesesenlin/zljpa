package com.zlj.zl.publics.home.controller;

import com.zlj.zl.util.base64.Base64Util;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
@RestController
@RequestMapping("/home")
public class HomeController {

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    @RequiresRoles(value = {"admin", "user", "superAdmin"}, logical = Logical.OR)
    public ModelAndView index(HttpServletRequest httpServletRequest) {
//        手动判断权限角色,此方法没有Logical.or的方式只有and
//        Subject subject = SecurityUtils.getSubject();
//            判断角色
//        subject.checkRole("admin");
//        并且关系
//        subject.checkRoles("admin","user","superAdmin");
//            判断权限
//        subject.checkPermissions("add","update");

        String token_user = "";
        Cookie[] cookies = httpServletRequest.getCookies();
        for (int i = 0; i < cookies.length; i++) {
            if (cookies[i].getName().equals("tokenu")) {
                token_user = cookies[i].getValue();
            }
        }
        return new ModelAndView("/home/index")
                .addObject("username", Base64Util.decode(token_user));
    }
}
