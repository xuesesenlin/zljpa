package com.zlj.zl.user.patentTypes.service;

import com.zlj.zl.user.patentTypes.model.PatentTypesModel;
import com.zlj.zl.util.resultJson.ResponseResult;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
public interface PatentTypesService {

    ResponseResult<PatentTypesModel> saves(List<PatentTypesModel> list);

    ResponseResult<PatentTypesModel> delete(String uuid);

    ResponseResult<PatentTypesModel> update(PatentTypesModel model);

    ResponseResult<Page<PatentTypesModel>> page(final PatentTypesModel model, int pageNow, int pageSize);

    ResponseResult<List<PatentTypesModel>> list(final PatentTypesModel model);
}
