package com.zlj.zl.user.companyTypes.service;

import com.zlj.zl.user.companyTypes.model.CompanyTypesModel;
import com.zlj.zl.util.resultJson.ResponseResult;

import java.util.List;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
public interface CompanyTypesService {

    ResponseResult<CompanyTypesModel> save(CompanyTypesModel model);

    ResponseResult<CompanyTypesModel> delete(String uuid);

    ResponseResult<CompanyTypesModel> update(CompanyTypesModel model);

    ResponseResult<List<CompanyTypesModel>> findList();

}
