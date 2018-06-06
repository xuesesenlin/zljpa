package com.zlj.zl.publics.stringFilte.service;

import com.zlj.zl.publics.stringFilte.model.StringFilterModel;
import com.zlj.zl.util.resultJson.ResponseResult;
import org.springframework.data.domain.Page;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
public interface StringFilterService {

    void save(StringFilterModel model);

    void delete(String uuid);

    void update(StringFilterModel model);

    ResponseResult<Page<StringFilterModel>> page(int pageNow, int PageSize);
}
