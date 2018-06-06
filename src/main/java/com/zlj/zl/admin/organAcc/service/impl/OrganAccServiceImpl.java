package com.zlj.zl.admin.organAcc.service.impl;

import com.zlj.zl.admin.organAcc.jpa.OrganAccJpa;
import com.zlj.zl.admin.organAcc.model.OrganAccModel;
import com.zlj.zl.admin.organAcc.service.OrganAccService;
import com.zlj.zl.publics.account.jpa.AccountJpa;
import com.zlj.zl.publics.account.model.AccountModel;
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
public class OrganAccServiceImpl implements OrganAccService {

    @Autowired
    private OrganAccJpa jpa;
    @Autowired
    private AccountJpa accountJpa;

    @Override
    public ResponseResult<OrganAccModel> add(OrganAccModel model) {
        ResponseResult<OrganAccModel> result = new ResponseResult<>();
        List<OrganAccModel> list = jpa.findByAccId(model.getAccId());
        if (list.size() > 0) {
            result.setSuccess(true);
            result.setMessage("此人已分配");
            return result;
        }
        jpa.save(model);
        result.setSuccess(true);
        result.setMessage("操作成功");
        return result;
    }

    @Override
    public ResponseResult<OrganAccModel> del(String id) {
        ResponseResult<OrganAccModel> result = new ResponseResult<>();
        int i = jpa.del(id);
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
    public ResponseResult<List<OrganAccModel>> findByAccId(String accId) {
        ResponseResult<List<OrganAccModel>> result = new ResponseResult<>();
        List<OrganAccModel> list = jpa.findByAccId(accId);
        if (list.size() > 0) {
            result.setSuccess(true);
            result.setMessage("操作成功");
            result.setData(list);
            return result;
        } else {
            result.setSuccess(false);
            result.setMessage("未查询到任何数据");
            result.setData(new ArrayList<>());
            return result;
        }
    }

    @Override
    public ResponseResult<List<OrganAccModel>> findByOrganId(String organId) {
        ResponseResult<List<OrganAccModel>> result = new ResponseResult<>();
        List<OrganAccModel> list = jpa.findByOrganId(organId);
        if (list.size() > 0) {
            result.setSuccess(true);
            result.setMessage("操作成功");
            result.setData(list);
            return result;
        } else {
            result.setSuccess(false);
            result.setMessage("未查询到任何数据");
            result.setData(new ArrayList<>());
            return result;
        }
    }

    @Override
    public ResponseResult<List<AccountModel>> findByNotOrgan() {
        ResponseResult<List<AccountModel>> result = new ResponseResult<>();
        List<AccountModel> list = accountJpa.findByNotOrgan();
        if (list.size() > 0) {
            result.setSuccess(true);
            result.setMessage("操作成功");
            result.setData(list);
            return result;
        } else {
            result.setSuccess(false);
            result.setMessage("未查询到任何数据");
            result.setData(new ArrayList<>());
            return result;
        }
    }
}
