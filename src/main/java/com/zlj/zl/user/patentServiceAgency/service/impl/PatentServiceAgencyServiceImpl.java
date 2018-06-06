package com.zlj.zl.user.patentServiceAgency.service.impl;

import com.zlj.zl.user.patentServiceAgency.jpa.PatentServiceAgencyJpa;
import com.zlj.zl.user.patentServiceAgency.model.PatentServiceAgencyModel;
import com.zlj.zl.user.patentServiceAgency.service.PatentServiceAgencyService;
import com.zlj.zl.util.resultJson.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
@Service
@Transactional
public class PatentServiceAgencyServiceImpl implements PatentServiceAgencyService {

    @Autowired
    private PatentServiceAgencyJpa jpa;

    @Override
    public ResponseResult<PatentServiceAgencyModel> save(PatentServiceAgencyModel model) {
        jpa.save(model);
        return new ResponseResult<>(true, "成功", null);
    }

    @Override
    public ResponseResult<PatentServiceAgencyModel> delete(String uuid) {
        jpa.delete(uuid);
        return new ResponseResult<>(true, "成功", null);
    }

    @Override
    public ResponseResult<PatentServiceAgencyModel> update(PatentServiceAgencyModel model) {
        PatentServiceAgencyModel one = jpa.getOne(model.getUuid());
        one.setTitles(model.getTitles());
        one.setBodys(model.getBodys());
        return new ResponseResult<>(true, "成功", null);
    }

    @Override
    public ResponseResult<PatentServiceAgencyModel> findOne(String uuid) {
        ResponseResult<PatentServiceAgencyModel> result = new ResponseResult<>();
//        不需要修改的情况下请尽量使用findOne，因为getOne会与数据库绑定，增加内存的使用
        PatentServiceAgencyModel one = jpa.findOne(uuid);
        if (one != null) {
            result.setSuccess(true);
            result.setMessage("成功");
            result.setData(one);
        } else {
            result.setSuccess(false);
            result.setMessage("未查询到数据");
        }
        return result;
    }

    @Override
    public ResponseResult<Page<PatentServiceAgencyModel>> page(PatentServiceAgencyModel model, int pageNow, int pageSize) {
        ResponseResult<Page<PatentServiceAgencyModel>> result = new ResponseResult<>();
        List<Sort.Order> orders = new ArrayList<>();
        orders.add(new Sort.Order(Sort.Direction.DESC, "titles"));//排序信息
        Pageable pageable = new PageRequest(pageNow, pageSize, new Sort(orders));  //分页信息
        Page<PatentServiceAgencyModel> page = jpa.findAll(pageable);
        if (page.getContent().size() > 0) {
            result.setSuccess(true);
            result.setMessage("成功");
            result.setData(page);
        } else {
            result.setSuccess(false);
            result.setMessage("未查询到数据");
        }
        return result;
    }
}
