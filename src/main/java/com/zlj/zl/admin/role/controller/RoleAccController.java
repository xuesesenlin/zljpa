package com.zlj.zl.admin.role.controller;

import com.zlj.zl.admin.role.model.RoleAccModel;
import com.zlj.zl.admin.role.service.RoleAccService;
import com.zlj.zl.util.resultJson.ResponseResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/roleAcc")
public class RoleAccController {

    @Autowired
    private RoleAccService service;

    @RequiresRoles(value = {"admin"})
    @RequestMapping(value = "/findByRoleId", method = RequestMethod.POST)
    public ResponseResult<List<RoleAccModel>> findByRoleId(@RequestParam("roleId") String roleId) {

        Subject subject = SecurityUtils.getSubject();
//            判断角色
        subject.checkRole("admin");

        return service.findByRoleId(roleId);
    }

    @RequiresRoles(value = {"admin"})
    @RequestMapping(value = "/setRD", method = RequestMethod.POST)
    public ResponseResult<String> setRD(@RequestParam("roleId") String roleId,
                                        @RequestParam("accountId") String accountId) {

        Subject subject = SecurityUtils.getSubject();
//            判断角色
        subject.checkRole("admin");

        RoleAccModel model = new RoleAccModel();
        model.setAccountId(accountId);
        model.setRoleId(roleId);
        model.setFlag("0");
        return service.setRD(model);
    }
}
