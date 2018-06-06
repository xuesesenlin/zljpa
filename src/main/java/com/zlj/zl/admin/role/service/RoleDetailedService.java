package com.zlj.zl.admin.role.service;

import com.zlj.zl.admin.jurisdiction.model.JurisdictionModel;
import com.zlj.zl.admin.role.model.RoleDetailedModel;
import com.zlj.zl.util.resultJson.ResponseResult;

import java.util.List;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
public interface RoleDetailedService {

    ResponseResult<String> setRD(RoleDetailedModel model);

    ResponseResult<List<RoleDetailedModel>> findByRoleId(String roleId);

    ResponseResult<List<JurisdictionModel>> findByRoleId2(String roleId);

    ResponseResult<List<RoleDetailedModel>> findByRoleIdAndJurId(String roleId, String jurId);

}
