package com.zlj.zl.publics.account.service.impl;

import com.zlj.zl.publics.account.jpa.AccountJpa;
import com.zlj.zl.publics.account.model.AccountModel;
import com.zlj.zl.publics.account.service.AccountService;
import com.zlj.zl.publics.login.model.LoginModel;
import com.zlj.zl.util.resultJson.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountJpa jpa;

    @Override
    public ResponseResult<AccountModel> add(AccountModel model) {
        AccountModel model1 = jpa.getByAccount(model.getAccount());

        if (model1 != null)
            return new ResponseResult<>(false, "账号已存在", null);

        jpa.save(model);
        return new ResponseResult<>(true, "成功", null);
    }

    @Override
    public ResponseResult<AccountModel> del(String uuid) {
        jpa.delete(uuid);
        return new ResponseResult<>(true, "成功", null);
    }

    @Override
    public ResponseResult<AccountModel> updatePWD(LoginModel model) {
        jpa.updatePWD(model.getUsername(), model.getPassword());
        return new ResponseResult<>(true, "成功", null);
    }

    @Override
    public ResponseResult<AccountModel> getByAccount(String account) {
        AccountModel model = jpa.getByAccount(account);
        if (model != null)
            return new ResponseResult<>(true, "成功", model);
        else
            return new ResponseResult<>(false, "未能查询到记录", null);
    }

    @Override
    public ResponseResult<AccountModel> getByAccountAndTypes(String account, String types) {
        AccountModel model = jpa.getByAccountAndTypes(account, types);
        if (model != null)
            return new ResponseResult<>(true, "成功", model);
        else
            return new ResponseResult<>(false, "未能查询到记录", null);
    }

    @Override
    public ResponseResult<Page<AccountModel>> page(int pageNow, int pageSize) {
        Pageable pageable = new PageRequest(pageNow, pageSize);
        Page<AccountModel> page = jpa.page(pageable);
        if (page.getContent().size() > 0)
            return new ResponseResult<>(true, "成功", page);
        else
            return new ResponseResult<>(false, "未能查询到记录", null);
    }

    @Override
    public ResponseResult<List<AccountModel>> pageByAccount(int pageNow, int pageSize, String account) {
        List<AccountModel> page = jpa.pageByAccount(account);
        if (page.size() > 0)
            return new ResponseResult<>(true, "成功", page);
        else
            return new ResponseResult<>(false, "未能查询到记录", null);
    }

    @Override
    public ResponseResult<List<AccountModel>> find() {
        List<AccountModel> list = jpa.find();
        if (list.size() > 0)
            return new ResponseResult<>(true, "成功", list);
        else
            return new ResponseResult<>(false, "未能查询到记录", new ArrayList<>());
    }
}
