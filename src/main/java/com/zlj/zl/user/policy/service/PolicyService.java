package com.zlj.zl.user.policy.service;

import com.zlj.zl.user.policy.model.PolicyModel;
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
public interface PolicyService {

    ResponseResult<PolicyModel> save(PolicyModel model);

    ResponseResult<PolicyModel> delete(String uuid);

    ResponseResult<PolicyModel> update(PolicyModel model);

    ResponseResult<PolicyModel> findOne(String uuid);

    ResponseResult<Page<PolicyModel>> page(int pageNow, int pageSize, String titles, String types);

    ResponseResult<List<PolicyTypeModel>> findPolicyType();

}
