package com.zlj.zl.admin.jurisdiction.controller;

import com.zlj.zl.admin.jurisdiction.model.JurisdictionModel;
import com.zlj.zl.admin.jurisdiction.service.JurisdictionService;
import com.zlj.zl.util.resultJson.ResponseResult;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author ld
 * @name 权限管理
 * @table
 * @remarks
 */
@RestController
@RequestMapping("/jurisdiction")
public class JurisdictionController {


    @Value("${page.pageSize}")
    private int pageSize;

    @Autowired
    private JurisdictionService service;

    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public ModelAndView init() {
        return new ModelAndView("/jurisdiction/index");
    }

    @RequiresRoles(value = {"admin", "user", "superAdmin"}, logical = Logical.OR)
    @RequestMapping(value = "/initData/{pageNow}", method = RequestMethod.POST)
    public ResponseResult<List<JurisdictionModel>> initData(@PathVariable(value = "pageNow") int pageNow,
                                                            @RequestParam(value = "seach", required = false) String seach) {

//        Subject subject = SecurityUtils.getSubject();
////            判断角色
//        subject.checkRole("admin");
//        并且关系
//        subject.checkRoles("admin","user","superAdmin");
//            判断权限
//        subject.checkPermissions("add","update");

        return service.pageByName(pageNow, 1000, seach);
    }
}
