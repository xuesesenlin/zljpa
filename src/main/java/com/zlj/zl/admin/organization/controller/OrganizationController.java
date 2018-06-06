package com.zlj.zl.admin.organization.controller;

import com.zlj.zl.admin.organization.model.OrganizationModel;
import com.zlj.zl.admin.organization.service.OrganizationService;
import com.zlj.zl.util.resultJson.ResponseResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/organization")
public class OrganizationController {

    @Autowired
    private OrganizationService service;

    @RequiresRoles(value = {"admin"})
    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public ModelAndView init() {

        Subject subject = SecurityUtils.getSubject();
//            判断角色
        subject.checkRole("admin");

        return new ModelAndView("/organization/index");
    }

    @RequiresRoles(value = {"admin"})
    @RequestMapping(value = "/findByParents", method = RequestMethod.POST)
    public ResponseResult<List<OrganizationModel>> findByParents(@RequestParam("parents") String parents) {

        Subject subject = SecurityUtils.getSubject();
//            判断角色
        subject.checkRole("admin");

        return service.findByParents(parents);
    }

    @RequiresRoles(value = {"admin"})
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseResult<OrganizationModel> add(@ModelAttribute("form") OrganizationModel model) {

        Subject subject = SecurityUtils.getSubject();
//            判断角色
        subject.checkRole("admin");

        return service.add(model);
    }

    @RequiresRoles(value = {"admin"})
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    public ResponseResult<OrganizationModel> del(@RequestParam("id") String id) {

        Subject subject = SecurityUtils.getSubject();
//            判断角色
        subject.checkRole("admin");

        return service.del(id);
    }

    @RequiresRoles(value = {"admin"})
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseResult<OrganizationModel> update(@ModelAttribute("form") OrganizationModel model) {

        Subject subject = SecurityUtils.getSubject();
//            判断角色
        subject.checkRole("admin");

        return service.update(model);
    }
}
