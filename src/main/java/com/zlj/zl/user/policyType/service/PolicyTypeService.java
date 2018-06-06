package com.zlj.zl.user.policyType.service;

import com.zlj.zl.user.policyType.model.PolicyTypeModel;
import com.zlj.zl.util.resultJson.ResponseResult;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
public interface PolicyTypeService {

    ResponseResult<PolicyTypeModel> add(PolicyTypeModel model);

    ResponseResult<PolicyTypeModel> del(String id);

    ResponseResult<PolicyTypeModel> update(PolicyTypeModel model);

    ResponseResult<List<PolicyTypeModel>> findByTypeName(String typeName);

    ResponseResult<Page<PolicyTypeModel>> page(int pageNow, int pageSize);

}
