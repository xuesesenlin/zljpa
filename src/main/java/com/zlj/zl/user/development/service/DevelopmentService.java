package com.zlj.zl.user.development.service;

import com.zlj.zl.user.development.model.DevelopmentModel;
import com.zlj.zl.util.resultJson.ResponseResult;
import org.springframework.data.domain.Page;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
public interface DevelopmentService {

    ResponseResult<DevelopmentModel> save(DevelopmentModel model);

    ResponseResult<DevelopmentModel> delete(String uuid);

    ResponseResult<DevelopmentModel> update(DevelopmentModel model);

    ResponseResult<DevelopmentModel> findOne(String uuid);

    ResponseResult<Page<DevelopmentModel>> page(int pageNow, int pageSize, String titles);

}
