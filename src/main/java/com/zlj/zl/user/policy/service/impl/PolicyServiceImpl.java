package com.zlj.zl.user.policy.service.impl;

import com.zlj.zl.user.policy.jpa.PolicyJpa;
import com.zlj.zl.user.policy.model.PolicyModel;
import com.zlj.zl.user.policy.service.PolicyService;
import com.zlj.zl.user.policyType.jpa.PolicyTypeJpa;
import com.zlj.zl.user.policyType.model.PolicyTypeModel;
import com.zlj.zl.util.resultJson.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
@Service
@Transactional
public class PolicyServiceImpl implements PolicyService {

    @Autowired
    private PolicyJpa jpa;
    @Autowired
    private PolicyTypeJpa policyTypeJpa;

    @Override
    public ResponseResult<PolicyModel> save(PolicyModel model) {
        ResponseResult<PolicyModel> result = new ResponseResult<>();
        jpa.save(model);
        result.setSuccess(true);
        result.setMessage("成功");
        return result;
    }

    @Override
    public ResponseResult<PolicyModel> delete(String uuid) {
        ResponseResult<PolicyModel> result = new ResponseResult<>();
        jpa.delete(uuid);
        result.setSuccess(true);
        result.setMessage("成功");
        return result;
    }

    @Override
    public ResponseResult<PolicyModel> update(PolicyModel model) {
        ResponseResult<PolicyModel> result = new ResponseResult<>();
        jpa.update(model.getTypes(), model.getTitles(), model.getBodys(), model.getUuid());
        result.setSuccess(true);
        result.setMessage("成功");
        return result;
    }

    @Override
    public ResponseResult<PolicyModel> findOne(String uuid) {
        ResponseResult<PolicyModel> result = new ResponseResult<>();
        PolicyModel model = jpa.findOne(uuid);
        if (model != null) {
            result.setSuccess(true);
            result.setMessage("成功");
            result.setData(model);
            return result;
        } else {
            result.setSuccess(false);
            result.setMessage("未找到数据");
            return result;
        }
    }

    @Override
    public ResponseResult<Page<PolicyModel>> page(int pageNow, int pageSize, String titles, String types) {
        ResponseResult<Page<PolicyModel>> result = new ResponseResult<>();
        Pageable pageable = new PageRequest(pageNow, pageSize);
        Page<PolicyModel> pageModel = null;
        if ((titles == null || titles.trim().equals("")) && (types == null || types.trim().equals(""))) {
            pageable = new PageRequest(pageNow, pageSize, Sort.Direction.ASC, "titles");
            pageModel = jpa.findAll(pageable);
        } else if (!(titles == null || titles.trim().equals("")) && (types == null || types.trim().equals(""))) {
            pageModel = jpa.findAllByPageable2("%" + titles + "%", pageable);
        } else if ((titles == null || titles.trim().equals("")) && !(types == null || types.trim().equals(""))) {
            pageModel = jpa.findAllByPageable3(types, pageable);
        } else
            pageModel = jpa.findAllByPageable("%" + titles + "%", types, pageable);
        if (pageModel.getContent().size() > 0) {
            result.setSuccess(true);
            result.setMessage("成功");
            result.setData(pageModel);
            return result;
        } else {
            result.setSuccess(false);
            result.setMessage("未查询到数据");
            return result;
        }
    }

    @Override
    public ResponseResult<List<PolicyTypeModel>> findPolicyType() {
        ResponseResult<List<PolicyTypeModel>> result = new ResponseResult<>();
        List<PolicyTypeModel> list = policyTypeJpa.findAll();
        if (list.size() > 0) {
            result.setSuccess(true);
            result.setMessage("成功");
            result.setData(list);
            return result;
        } else {
            result.setSuccess(false);
            result.setMessage("未查询到数据");
            return result;
        }
    }
}
