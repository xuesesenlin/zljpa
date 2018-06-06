package com.zlj.zl.user.patentTypes.service.impl;

import com.zlj.zl.user.patentTypes.jpa.PatentTypesJpa;
import com.zlj.zl.user.patentTypes.model.PatentTypesModel;
import com.zlj.zl.user.patentTypes.service.PatentTypesService;
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
public class PatentTypesServiceImpl implements PatentTypesService {

    @Autowired
    private PatentTypesJpa jpa;

    @Override
    public ResponseResult<PatentTypesModel> saves(List<PatentTypesModel> list) {
        jpa.save(list);
        return new ResponseResult<>(true, "成功", null);
    }

    @Override
    public ResponseResult<PatentTypesModel> delete(String uuid) {
        jpa.delete(uuid);
        return new ResponseResult<>(true, "成功", null);
    }

    @Override
    public ResponseResult<PatentTypesModel> update(PatentTypesModel model) {
//        此处必须用getOne不能用findOne，get为持久实体,find为返回值
        PatentTypesModel putModel = jpa.getOne(model.getUuid());
        putModel.setTypesName(model.getTypesName());
        return new ResponseResult<>(true, "成功", null);
    }

    @Override
    public ResponseResult<Page<PatentTypesModel>> page(PatentTypesModel model, int pageNow, int pageSize) {
        List<Sort.Order> orders = new ArrayList<>();
        orders.add(new Sort.Order(Sort.Direction.DESC, "typesName"));//排序信息
        Pageable pageable = new PageRequest(pageNow, pageSize, new Sort(orders));  //分页信息
//jpa分页
        Page<PatentTypesModel> page = jpa.findAll(pageable);
        ResponseResult<Page<PatentTypesModel>> result = new ResponseResult<>();
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
    public ResponseResult<List<PatentTypesModel>> list(PatentTypesModel model) {
        ResponseResult<List<PatentTypesModel>> result = new ResponseResult<>();
        Specification<PatentTypesModel> spec = new Specification<PatentTypesModel>() {        //查询条件构造
            @Override
            public Predicate toPredicate(Root<PatentTypesModel> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                if (model.getTypesName() != null && !model.getTypesName().trim().equals("")) {
                    Predicate p1 = cb.equal(root.get("typesName").as(String.class), model.getTypesName());
                    Predicate p = cb.and(p1);
                    return p;
                } else
                    return null;
            }
        };
        List<PatentTypesModel> list = jpa.findAll(spec);
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
