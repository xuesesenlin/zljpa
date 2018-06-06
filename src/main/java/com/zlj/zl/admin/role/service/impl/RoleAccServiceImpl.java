package com.zlj.zl.admin.role.service.impl;

import com.zlj.zl.admin.role.jpa.RoleAccJpa;
import com.zlj.zl.admin.role.model.RoleAccModel;
import com.zlj.zl.admin.role.service.RoleAccService;
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
public class RoleAccServiceImpl implements RoleAccService {

    @Autowired
    private RoleAccJpa jpa;

    @Override
    public ResponseResult<String> setRD(RoleAccModel model) {
        ResponseResult<String> result = new ResponseResult<>();
        List<RoleAccModel> list = jpa.findByAccountId(model.getAccountId());
        if (list.size() > 0) {
            int i = jpa.delByAccountId(model.getAccountId());
            switch (i) {
                case 0:
                    result.setSuccess(false);
                    result.setMessage("未操作任何数据");
                    return result;
                case 1:
                    if (list.get(0).getAccountId().equals(model.getAccountId())
                            && list.get(0).getRoleId().equals(model.getRoleId())) {
                        result.setSuccess(true);
                        result.setMessage("操作成功");
                        result.setData("0");
                        return result;
                    } else
                        break;
                default:
                    result.setSuccess(false);
                    result.setMessage("操作失败");
                    return result;
            }
        }
        jpa.save(model);
        result.setSuccess(true);
        result.setMessage("操作成功");
        result.setData("1");
        return result;
    }

    @Override
    public ResponseResult<List<RoleAccModel>> findByAccountId(String accountId) {
        ResponseResult<List<RoleAccModel>> result = new ResponseResult<>();
        List<RoleAccModel> list = jpa.findByAccountId(accountId);
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
    public ResponseResult<List<RoleAccModel>> findByRoleId(String roleId) {
        ResponseResult<List<RoleAccModel>> result = new ResponseResult<>();
        List<RoleAccModel> list = jpa.findByRoleId(roleId);
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
