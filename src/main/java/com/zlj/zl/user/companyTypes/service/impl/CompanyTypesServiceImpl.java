package com.zlj.zl.user.companyTypes.service.impl;

import com.zlj.zl.user.companyTypes.jpa.CompanyTypesJpa;
import com.zlj.zl.user.companyTypes.model.CompanyTypesModel;
import com.zlj.zl.user.companyTypes.service.CompanyTypesService;
import com.zlj.zl.util.resultJson.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
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
public class CompanyTypesServiceImpl implements CompanyTypesService {

    @Autowired
    private CompanyTypesJpa jpa;

    @Override
    public ResponseResult<CompanyTypesModel> save(CompanyTypesModel model) {
        List<CompanyTypesModel> list = jpa.findByCompanyTypes(model.getCompanyTypes());
        if (list.size() > 0)
            return new ResponseResult<>(false, "名称重复", null);
        jpa.save(model);
        return new ResponseResult<>(true, "成功", null);
    }

    @Override
    public ResponseResult<CompanyTypesModel> delete(String uuid) {
        jpa.delete(uuid);
        return new ResponseResult<>(true, "成功", null);
    }

    @Override
    public ResponseResult<CompanyTypesModel> update(CompanyTypesModel model) {
        CompanyTypesModel one = jpa.getOne(model.getUuid());
        one.setCompanyTypes(model.getCompanyTypes());
        return new ResponseResult<>(true, "成功", null);
    }

    @Override
    public ResponseResult<List<CompanyTypesModel>> findList() {
        List<CompanyTypesModel> list = jpa.findAll();
        if (list.size() > 0)
            return new ResponseResult<>(true, "成功", list);
        return new ResponseResult<>(false, "未获取数据", null);
    }
}
