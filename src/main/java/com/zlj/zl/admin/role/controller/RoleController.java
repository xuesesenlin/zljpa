package com.zlj.zl.admin.role.controller;

import com.zlj.zl.admin.role.model.RoleModel;
import com.zlj.zl.admin.role.service.RoleService;
import com.zlj.zl.util.resultJson.ResponseResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
@RestController
@RequestMapping("/role")
public class RoleController {


    @Value("${page.pageSize}")
    private int pageSize;

    @Autowired
    private RoleService service;

    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public ModelAndView init() {

        Subject subject = SecurityUtils.getSubject();
//            判断角色
        subject.checkRole("admin");

        return new ModelAndView("/role/index");
    }

    @RequiresRoles(value = {"admin"})
    @RequestMapping(value = "/initData/{pageNow}", method = RequestMethod.POST)
    public ResponseResult<List<RoleModel>> initData(@PathVariable(value = "pageNow") int pageNow,
                                                    @RequestParam(value = "seach", required = false) String seach) {

        Subject subject = SecurityUtils.getSubject();
//            判断角色
        subject.checkRole("admin");

        return service.pageByName(pageNow, pageSize, seach);
    }

    @RequiresRoles(value = {"admin"})
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseResult<RoleModel> add(@ModelAttribute("form") RoleModel model) {

        Subject subject = SecurityUtils.getSubject();
//            判断角色
        subject.checkRole("admin");

        return service.add(model);
    }

    @RequiresRoles(value = {"admin"})
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    public ResponseResult<RoleModel> del(@RequestParam("id") String id) {

        Subject subject = SecurityUtils.getSubject();
//            判断角色
        subject.checkRole("admin");

        return service.del(id);
    }

    @RequiresRoles(value = {"admin"})
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseResult<RoleModel> update(@ModelAttribute("form") RoleModel model) {

        Subject subject = SecurityUtils.getSubject();
//            判断角色
        subject.checkRole("admin");

        return service.update(model);
    }
}
