package com.zlj.zl.user.applyPeoTypes.service.impl;

import com.zlj.zl.user.applyPeoTypes.jpa.ApplyPeoTypesJpa;
import com.zlj.zl.user.applyPeoTypes.model.ApplyPeoTypesModel;
import com.zlj.zl.user.applyPeoTypes.service.ApplyPeoTypesService;
import com.zlj.zl.util.resultJson.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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
public class ApplyPeoTypesImpl implements ApplyPeoTypesService {

    @Autowired
    private ApplyPeoTypesJpa jpa;

    @Override
    public ResponseResult<ApplyPeoTypesModel> saves(List<ApplyPeoTypesModel> list) {
        jpa.save(list);
        return new ResponseResult<>(true, "成功", null);
    }

    @Override
    public ResponseResult<ApplyPeoTypesModel> delete(String uuid) {
        jpa.delete(uuid);
        return new ResponseResult<>(true, "成功", null);
    }

    @Override
    public ResponseResult<ApplyPeoTypesModel> update(ApplyPeoTypesModel model) {
//        此处必须用getOne不能用findOne，get为持久实体,find为返回值
        ApplyPeoTypesModel putModel = jpa.getOne(model.getUuid());
        putModel.setTypesName(model.getTypesName());
        return new ResponseResult<>(true, "成功", null);
    }

    @Override
    public ResponseResult<Page<ApplyPeoTypesModel>> page(ApplyPeoTypesModel model, int pageNow, int pageSize) {
        List<Sort.Order> orders = new ArrayList<>();
        orders.add(new Sort.Order(Sort.Direction.DESC, "typesName"));//排序信息
        Pageable pageable = new PageRequest(pageNow, pageSize, new Sort(orders));  //分页信息
//jpa分页
        Page<ApplyPeoTypesModel> page = jpa.findAll(pageable);
        ResponseResult<Page<ApplyPeoTypesModel>> result = new ResponseResult<>();
        if (page.getContent().size() > 0) {
            result.setSuccess(true);
            result.setMessage("成功");
            result.setData(page);
            return result;
        } else {
            result.setSuccess(false);
            result.setMessage("未查询到数据");
            return result;
        }
    }

    @Override
    public ResponseResult<List<ApplyPeoTypesModel>> list(ApplyPeoTypesModel model) {
        ResponseResult<List<ApplyPeoTypesModel>> result = new ResponseResult<>();
        Specification<ApplyPeoTypesModel> spec = new Specification<ApplyPeoTypesModel>() {        //查询条件构造
            @Override
            public Predicate toPredicate(Root<ApplyPeoTypesModel> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                if (model.getTypesName() != null && !model.getTypesName().trim().equals("")) {
                    Predicate p1 = cb.equal(root.get("typesName").as(String.class), model.getTypesName());
                    Predicate p = cb.and(p1);
                    return p;
                } else
                    return null;
            }
        };
        List<ApplyPeoTypesModel> list = jpa.findAll(spec);
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
