package com.zlj.zl.admin.role.service.impl;

import com.zlj.zl.admin.jurisdiction.jpa.JurisdictionJpa;
import com.zlj.zl.admin.jurisdiction.model.JurisdictionModel;
import com.zlj.zl.admin.role.jpa.RoleDetailedJpa;
import com.zlj.zl.admin.role.model.RoleDetailedModel;
import com.zlj.zl.admin.role.service.RoleDetailedService;
import com.zlj.zl.util.resultJson.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
@Service
@Transactional
public class RoleDetailedServiceImpl implements RoleDetailedService {

    @Autowired
    private RoleDetailedJpa jpa;
    @Autowired
    private JurisdictionJpa jurisdictionJpa;

    @Override
    public ResponseResult<String> setRD(RoleDetailedModel model) {
        ResponseResult<String> result = new ResponseResult<>();
        List<RoleDetailedModel> list = jpa.findByRoleIdAndJurId(model.getRoleId(), model.getJurId());
        if (list.size() > 0) {
            int i = jpa.delByJurIdAndRoleId(model.getRoleId(), model.getJurId());
            switch (i) {
                case 0:
                    result.setSuccess(false);
                    result.setMessage("未操作任何数据");
                    return result;
                case 1:
                    result.setSuccess(true);
                    result.setMessage("操作成功");
                    result.setData("0");
                    return result;
                default:
                    result.setSuccess(false);
                    result.setMessage("操作失败");
                    return result;
            }
        } else {
            jpa.save(model);
            result.setSuccess(true);
            result.setMessage("操作成功");
            result.setData("1");
            return result;
        }
    }

    @Override
    public ResponseResult<List<RoleDetailedModel>> findByRoleId(String roleId) {
        ResponseResult<List<RoleDetailedModel>> result = new ResponseResult<>();
        List<RoleDetailedModel> list = jpa.findByRoleId(roleId);
        if (list.size() > 0) {
            result.setSuccess(true);
            result.setMessage("操作成功");
            result.setData(list);
            return result;
        } else {
            result.setSuccess(false);
            result.setMessage("未获取到数据");
            result.setData(new ArrayList<>());
            return result;
        }
    }

    @Override
    public ResponseResult<List<JurisdictionModel>> findByRoleId2(String roleId) {
        ResponseResult<List<JurisdictionModel>> result = new ResponseResult<>();
        List<JurisdictionModel> list = jurisdictionJpa.findByRoleId(roleId);
        if (list.size() > 0) {
            result.setSuccess(true);
            result.setMessage("操作成功");
            result.setData(list);
            return result;
        } else {
            result.setSuccess(false);
            result.setMessage("未获取到数据");
            result.setData(new ArrayList<>());
            return result;
        }
    }

    @Override
    public ResponseResult<List<RoleDetailedModel>> findByRoleIdAndJurId(String roleId, String jurId) {
        ResponseResult<List<RoleDetailedModel>> result = new ResponseResult<>();
        List<RoleDetailedModel> list = jpa.findByRoleIdAndJurId(roleId, jurId);
        if (list.size() > 0) {
            result.setSuccess(true);
            result.setMessage("操作成功");
            result.setData(list);
            return result;
        } else {
            result.setSuccess(false);
            result.setMessage("未获取到数据");
            result.setData(new ArrayList<>());
            return result;
        }
    }
}
