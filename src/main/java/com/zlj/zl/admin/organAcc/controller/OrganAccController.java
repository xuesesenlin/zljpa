package com.zlj.zl.admin.organAcc.controller;

import com.zlj.zl.admin.organAcc.model.OrganAccModel;
import com.zlj.zl.admin.organAcc.service.OrganAccService;
import com.zlj.zl.publics.account.model.AccountModel;
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
@RequestMapping("/organAcc")
public class OrganAccController {

    @Autowired
    private OrganAccService service;

    @RequiresRoles(value = {"admin"})
    @RequestMapping(value = "/findByOrganId", method = RequestMethod.POST)
    public ResponseResult<List<OrganAccModel>> findByOrganId(@RequestParam("organId") String organId) {
        Subject subject = SecurityUtils.getSubject();
//            判断角色
        subject.checkRole("admin");
        return service.findByOrganId(organId);
    }

    @RequiresRoles(value = {"admin"})
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseResult<OrganAccModel> add(@RequestParam("organId") String organId,
                                             @RequestParam("accId") String accId) {

        Subject subject = SecurityUtils.getSubject();
//            判断角色
        subject.checkRole("admin");

        OrganAccModel model = new OrganAccModel();
        model.setAccId(accId);
        model.setOrgId(organId);
        model.setFlag("0");
        return service.add(model);
    }

    @RequiresRoles(value = {"admin"})
    @RequestMapping(value = "/findByNotOrgan", method = RequestMethod.GET)
    public ResponseResult<List<AccountModel>> findByNotOrgan() {

        Subject subject = SecurityUtils.getSubject();
//            判断角色
        subject.checkRole("admin");

        return service.findByNotOrgan();
    }

    @RequiresRoles(value = {"admin"})
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    public ResponseResult<OrganAccModel> del(@RequestParam("id") String id) {

        Subject subject = SecurityUtils.getSubject();
//            判断角色
        subject.checkRole("admin");

        return service.del(id);
    }
}
