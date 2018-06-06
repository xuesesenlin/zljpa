package com.zlj.zl.publics.account.controller;

import com.zlj.zl.publics.account.model.AccountModel;
import com.zlj.zl.publics.account.service.AccountService;
import com.zlj.zl.publics.login.model.LoginModel;
import com.zlj.zl.util.base64.Base64Util;
import com.zlj.zl.util.resultJson.ResponseResult;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;
import java.util.Set;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService service;

    @RequiresRoles(value = {"admin", "user", "superAdmin"}, logical = Logical.OR)
    @RequestMapping(value = "/toPWD", method = RequestMethod.GET)
    public ModelAndView toPWD() {
        return new ModelAndView("/account/pwd");
    }

    @RequiresRoles(value = {"admin", "user", "superAdmin"}, logical = Logical.OR)
    @RequestMapping(value = "/pwd", method = RequestMethod.POST)
    public ResponseResult pwd(@RequestParam("password1") String password1,
                              @RequestParam("password2") String password2,
                              HttpServletRequest request) {

        ResponseResult<AccountModel> result = new ResponseResult<>();

        Cookie[] cookies = request.getCookies();
        String tokenu = "";
        for (int i = 0; i < cookies.length; i++) {
            if (cookies[i].getName().equals("tokenu")) {
                tokenu = cookies[i].getValue();
                continue;
            }
        }
        if (tokenu.trim().isEmpty()) {
            result.setSuccess(false);
            result.setMessage("登录超时,请从新登录!");
            return result;
        }

        if (password1 == null
                || password2 == null
                || password1.trim().isEmpty()
                || !password1.equals(password2)) {
            result.setSuccess(false);
            result.setMessage("请完善信息且两次密码必须一致!");
            return result;
        }

        LoginModel model = null;
        ResponseResult<AccountModel> result1 = service.getByAccount(Base64Util.decode(tokenu));
        if (result1.isSuccess()) {
            model = new LoginModel();
            model.setUsername(result1.getData().getAccount());
            model.setPassword(password1);
        } else {
            result.setSuccess(false);
            result.setMessage("当前登录账户未找到");
            return result;
        }

//        jsr-303验证
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validator = vf.getValidator();
        Set<ConstraintViolation<LoginModel>> set = validator.validate(model);
        for (ConstraintViolation<LoginModel> constraintViolation : set) {
            result.setSuccess(false);
            result.setMessage(constraintViolation.getMessage());
            return result;
        }
        model.setPassword(Base64Util.encode(model.getPassword()));
        return service.updatePWD(model);
    }

    @RequiresRoles(value = {"admin", "user", "superAdmin"}, logical = Logical.OR)
    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public ResponseResult<List<AccountModel>> find() {
        return service.find();
    }
}
