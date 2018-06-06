package com.zlj.zl.user.patentDataPut.service;

import com.zlj.zl.user.patentDataPut.model.PatentDataPutModel;
import com.zlj.zl.user.patentDataPut.model.PatentDataPutQueryModel;
import com.zlj.zl.util.resultJson.ResponseResult;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
public interface PatentDataPutService {

    ResponseResult<PatentDataPutModel> saves(List<PatentDataPutModel> list);

    ResponseResult<PatentDataPutModel> delete(String uuid);

    ResponseResult<PatentDataPutModel> update(PatentDataPutModel model);

    ResponseResult<PatentDataPutModel> updates(List<PatentDataPutModel> model, int lx);

    ResponseResult<Page<PatentDataPutModel>> page(final PatentDataPutQueryModel model, int pageNow, int pageSize);

    ResponseResult<List<PatentDataPutModel>> list(final PatentDataPutQueryModel model);

    ResponseResult<List<PatentDataPutModel>> findByApplyCode(String applyCode);

    ResponseResult<List<PatentDataPutModel>> findByImpDate(long impDate);
}
