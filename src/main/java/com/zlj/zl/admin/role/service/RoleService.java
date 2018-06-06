package com.zlj.zl.admin.role.service;

import com.zlj.zl.admin.role.model.RoleModel;
import com.zlj.zl.util.resultJson.ResponseResult;

import java.util.List;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
public interface RoleService {

    ResponseResult<RoleModel> add(RoleModel model);

    ResponseResult<RoleModel> del(String id);

    ResponseResult<RoleModel> update(RoleModel model);

    ResponseResult<List<RoleModel>> pageByName(int pageNow, int pageSize, String seach);
}
