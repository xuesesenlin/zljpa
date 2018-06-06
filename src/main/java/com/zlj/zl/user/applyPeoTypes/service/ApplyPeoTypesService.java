package com.zlj.zl.user.applyPeoTypes.service;

import com.zlj.zl.user.applyPeoTypes.model.ApplyPeoTypesModel;
import com.zlj.zl.util.resultJson.ResponseResult;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
public interface ApplyPeoTypesService {

    ResponseResult<ApplyPeoTypesModel> saves(List<ApplyPeoTypesModel> list);

    ResponseResult<ApplyPeoTypesModel> delete(String uuid);

    ResponseResult<ApplyPeoTypesModel> update(ApplyPeoTypesModel model);

    ResponseResult<Page<ApplyPeoTypesModel>> page(final ApplyPeoTypesModel model, int pageNow, int pageSize);

    ResponseResult<List<ApplyPeoTypesModel>> list(final ApplyPeoTypesModel model);
}
