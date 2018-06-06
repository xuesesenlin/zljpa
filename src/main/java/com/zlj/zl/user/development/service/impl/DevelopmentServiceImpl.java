package com.zlj.zl.user.development.service.impl;

import com.zlj.zl.user.development.jpa.DevelopmentJpa;
import com.zlj.zl.user.development.model.DevelopmentModel;
import com.zlj.zl.user.development.service.DevelopmentService;
import com.zlj.zl.util.resultJson.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
@Service
@Transactional
public class DevelopmentServiceImpl implements DevelopmentService {

    @Autowired
    private DevelopmentJpa jpa;

    @Override
    public ResponseResult<DevelopmentModel> save(DevelopmentModel model) {
        ResponseResult<DevelopmentModel> result = new ResponseResult<>();
        jpa.save(model);
        result.setSuccess(true);
        result.setMessage("成功");
        return result;
    }

    @Override
    public ResponseResult<DevelopmentModel> delete(String uuid) {
        ResponseResult<DevelopmentModel> result = new ResponseResult<>();
        jpa.delete(uuid);
        result.setSuccess(true);
        result.setMessage("成功");
        return result;
    }

    @Override
    public ResponseResult<DevelopmentModel> update(DevelopmentModel model) {
        ResponseResult<DevelopmentModel> result = new ResponseResult<>();
        jpa.update(model.getTitles(), model.getBodys(), model.getUuid());
        result.setSuccess(true);
        result.setMessage("成功");
        return result;
    }

    @Override
    public ResponseResult<DevelopmentModel> findOne(String uuid) {
        ResponseResult<DevelopmentModel> result = new ResponseResult<>();
        DevelopmentModel model = jpa.findOne(uuid);
        if (model != null) {
            result.setSuccess(true);
            result.setMessage("成功");
            result.setData(model);
            return result;
        } else {
            result.setSuccess(false);
            result.setMessage("未获取到数据");
            return result;
        }
    }

    @Override
    public ResponseResult<Page<DevelopmentModel>> page(int pageNow, int pageSize, String titles) {
        ResponseResult<Page<DevelopmentModel>> result = new ResponseResult<>();
        Pageable pageable = new PageRequest(pageNow, pageSize);
        Page<DevelopmentModel> pageModel = null;
        if (titles == null || titles.trim().equals("")) {
            pageable = new PageRequest(pageNow, pageSize, Sort.Direction.ASC, "titles");
            pageModel = jpa.findAll(pageable);
        } else
            pageModel = jpa.findAllByPageable("%" + titles + "%", pageable);
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
}
