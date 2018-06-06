package com.zlj.zl.admin.organAcc.service;

import com.zlj.zl.admin.organAcc.model.OrganAccModel;
import com.zlj.zl.publics.account.model.AccountModel;
import com.zlj.zl.util.resultJson.ResponseResult;

import java.util.List;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
public interface OrganAccService {

    ResponseResult<OrganAccModel> add(OrganAccModel model);

    ResponseResult<OrganAccModel> del(String id);

    ResponseResult<List<OrganAccModel>> findByAccId(String accId);

    ResponseResult<List<OrganAccModel>> findByOrganId(String organId);

    ResponseResult<List<AccountModel>> findByNotOrgan();
}
