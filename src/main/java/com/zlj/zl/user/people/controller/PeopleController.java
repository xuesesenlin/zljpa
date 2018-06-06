package com.zlj.zl.user.people.controller;

import com.zlj.zl.publics.account.model.AccountModel;
import com.zlj.zl.publics.account.service.AccountService;
import com.zlj.zl.publics.login.model.LoginModel;
import com.zlj.zl.util.base64.Base64Util;
import com.zlj.zl.util.resultJson.ResponseResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
@RestController
@RequestMapping("/people")
public class PeopleController {

    @Value("${page.pageSize}")
    private int pageSize;

    @Autowired
    private AccountService service;

    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public ModelAndView init() {
        return new ModelAndView("/people/index");
    }

    @RequestMapping(value = "/people/{pageNow}", method = RequestMethod.GET)
    public ResponseResult<Page<AccountModel>> page(@PathVariable("pageNow") int pageNow) {
        return service.page(pageNow, pageSize);
    }

    @RequestMapping(value = "/people/{id}", method = RequestMethod.DELETE)
    public ResponseResult<AccountModel> delete(@PathVariable("id") String id) {
        return service.del(id);
    }

    @RequestMapping(value = "/people", method = RequestMethod.POST)
    public ResponseResult<AccountModel> save(@Valid @ModelAttribute("form") LoginModel model,
                                             BindingResult bindingResult) {
        ResponseResult<AccountModel> result = new ResponseResult<>();
        if (bindingResult.hasErrors()) {
            result.setSuccess(false);
            result.setMessage(bindingResult.getFieldError().getDefaultMessage());
            return result;
        }
        AccountModel model1 = new AccountModel();
        model1.setAccount(model.getUsername());
        model1.setPassword(Base64Util.encode(model.getPassword()));
        if (model.getTypes().equals("1")) {
            Subject subject = SecurityUtils.getSubject();
//            判断是否拥有新增管理员的权限
            subject.checkPermission("people:data:save_admin_data");
            model1.setTypes("admin");
        } else
            model1.setTypes("user");
        model1.setFlag("0");
        model1.setSystime(System.currentTimeMillis());
        return service.add(model1);
    }
}
