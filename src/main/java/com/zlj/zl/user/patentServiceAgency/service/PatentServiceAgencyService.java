package com.zlj.zl.user.patentServiceAgency.service;

import com.zlj.zl.user.patentServiceAgency.model.PatentServiceAgencyModel;
import com.zlj.zl.util.resultJson.ResponseResult;
import org.springframework.data.domain.Page;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
public interface PatentServiceAgencyService {

    ResponseResult<PatentServiceAgencyModel> save(PatentServiceAgencyModel model);

    ResponseResult<PatentServiceAgencyModel> delete(String uuid);

    ResponseResult<PatentServiceAgencyModel> update(PatentServiceAgencyModel model);

    ResponseResult<PatentServiceAgencyModel> findOne(String uuid);

    ResponseResult<Page<PatentServiceAgencyModel>> page(final PatentServiceAgencyModel model, int pageNow, int pageSize);
}
