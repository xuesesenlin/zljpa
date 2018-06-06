package com.zlj.zl.admin.organization.service;

import com.zlj.zl.admin.organization.model.OrganizationModel;
import com.zlj.zl.util.resultJson.ResponseResult;

import java.util.List;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
public interface OrganizationService {

    ResponseResult<OrganizationModel> add(OrganizationModel model);

    ResponseResult<OrganizationModel> del(String id);

    ResponseResult<OrganizationModel> update(OrganizationModel model);

    ResponseResult<List<OrganizationModel>> findByParents(String parents);
}
