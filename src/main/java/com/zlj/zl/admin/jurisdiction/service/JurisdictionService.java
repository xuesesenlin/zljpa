package com.zlj.zl.admin.jurisdiction.service;

import com.zlj.zl.admin.jurisdiction.model.JurisdictionModel;
import com.zlj.zl.util.resultJson.ResponseResult;

import java.util.List;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
public interface JurisdictionService {

    ResponseResult<JurisdictionModel> add(JurisdictionModel model);

    ResponseResult<JurisdictionModel> del(String id);

    ResponseResult<JurisdictionModel> update(JurisdictionModel model);

    ResponseResult<JurisdictionModel> getById(String id);

    ResponseResult<List<JurisdictionModel>> findByParents(String parents);

    ResponseResult<List<JurisdictionModel>> findBySysType(String sysType);

    ResponseResult<List<JurisdictionModel>> findByJurType(int jurType);

    ResponseResult<List<JurisdictionModel>> pageByName(int pageNow, int pageSize, String name);
}
