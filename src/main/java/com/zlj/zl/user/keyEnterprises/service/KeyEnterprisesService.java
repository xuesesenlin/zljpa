package com.zlj.zl.user.keyEnterprises.service;

import com.zlj.zl.user.keyEnterprises.model.KeyEnterprisesModel;
import com.zlj.zl.util.resultJson.ResponseResult;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
public interface KeyEnterprisesService {

    ResponseResult<KeyEnterprisesModel> put(KeyEnterprisesModel model);

    ResponseResult<KeyEnterprisesModel> delete(String uuid);

    ResponseResult<Page<KeyEnterprisesModel>> page(int pageNumber, int pageSize);

    ResponseResult<List<KeyEnterprisesModel>> list(final KeyEnterprisesModel model);
}
