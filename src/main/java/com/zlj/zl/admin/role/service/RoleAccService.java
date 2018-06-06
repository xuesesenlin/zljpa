package com.zlj.zl.admin.role.service;

import com.zlj.zl.admin.role.model.RoleAccModel;
import com.zlj.zl.util.resultJson.ResponseResult;

import java.util.List;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
public interface RoleAccService {

    ResponseResult<String> setRD(RoleAccModel model);

    ResponseResult<List<RoleAccModel>> findByAccountId(String accountId);

    ResponseResult<List<RoleAccModel>> findByRoleId(String roleId);
}
