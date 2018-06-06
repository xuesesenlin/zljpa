package com.zlj.zl.user.PMC.service;

import com.zlj.zl.user.PMC.model.PMCModel;
import com.zlj.zl.util.resultJson.ResponseResult;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
public interface PMCService {

    ResponseResult<Page<PMCModel>> findByPageAndParams(final PMCModel param, int pageNumber, int pageSize);

    ResponseResult<List<PMCModel>> findByPageAndParamsList(final PMCModel param);

    ResponseResult<PMCModel> save(PMCModel model);

    ResponseResult<PMCModel> delete(String uuid);

    ResponseResult<PMCModel> update(PMCModel model);

    List<PMCModel> findByCityName(String names);

}
