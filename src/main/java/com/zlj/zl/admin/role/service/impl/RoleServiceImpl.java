package com.zlj.zl.admin.role.service.impl;

import com.zlj.zl.admin.role.jpa.RoleJpa;
import com.zlj.zl.admin.role.model.RoleModel;
import com.zlj.zl.admin.role.service.RoleService;
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
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleJpa jpa;

    @Override
    public ResponseResult<RoleModel> add(RoleModel model) {
        ResponseResult<RoleModel> result = new ResponseResult<>();
        jpa.save(model);
        result.setSuccess(true);
        result.setMessage("操作成功");
        return result;
    }

    @Override
    public ResponseResult<RoleModel> del(String id) {
        ResponseResult<RoleModel> result = new ResponseResult<>();
        jpa.delete(id);
        result.setSuccess(true);
        result.setMessage("操作成功");
        return result;
    }

    @Override
    public ResponseResult<RoleModel> update(RoleModel model) {
        ResponseResult<RoleModel> result = new ResponseResult<>();
        int i = jpa.update(model.getName(), model.getUuid());
        switch (i) {
            case 0:
                result.setSuccess(false);
                result.setMessage("未操作任何数据");
                return result;
            case 1:
                result.setSuccess(true);
                result.setMessage("操作成功");
                return result;
            default:
                result.setSuccess(false);
                result.setMessage("操作失败");
                return result;
        }
    }

    @Override
    public ResponseResult<List<RoleModel>> pageByName(int pageNow, int pageSize, String seach) {
        ResponseResult<List<RoleModel>> result = new ResponseResult<>();
        List<RoleModel> page;
        if (seach == null || seach.trim().equals(""))
            page = jpa.page();
        else
            page = jpa.pageByName(seach);
        if (page.size() > 0) {
            result.setSuccess(true);
            result.setMessage("操作成功");
            result.setData(page);
            return result;
        } else {
            result.setSuccess(false);
            result.setMessage("未获取到任何数据");
            result.setData(new ArrayList<>());
            return result;
        }
    }
}
