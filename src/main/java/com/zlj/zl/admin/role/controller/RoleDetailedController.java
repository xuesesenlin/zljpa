package com.zlj.zl.admin.role.controller;

import com.zlj.zl.admin.role.model.RoleDetailedModel;
import com.zlj.zl.admin.role.service.RoleDetailedService;
import com.zlj.zl.util.resultJson.ResponseResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
@RestController
@RequestMapping("/roleDetailed")
public class RoleDetailedController {


    @Value("${page.pageSize}")
    private int pageSize;

    @Autowired
    private RoleDetailedService service;

    @RequiresRoles(value = {"admin"})
    @RequestMapping(value = "/findByRoleId", method = RequestMethod.POST)
    public ResponseResult<List<RoleDetailedModel>> findByRoleId(@RequestParam(value = "roleId") String roleId) {

        Subject subject = SecurityUtils.getSubject();
//            判断角色
        subject.checkRole("admin");

        return service.findByRoleId(roleId);
    }

    @RequiresRoles(value = {"admin"})
    @RequestMapping(value = "/setRD", method = RequestMethod.POST)
    public ResponseResult<String> setRD(@RequestParam(value = "roleId") String roleId,
                                        @RequestParam(value = "jurId") String jurId) {

        Subject subject = SecurityUtils.getSubject();
//            判断角色
        subject.checkRole("admin");

        RoleDetailedModel model = new RoleDetailedModel();
        model.setJurId(jurId);
        model.setRoleId(roleId);
        return service.setRD(model);
    }

}
