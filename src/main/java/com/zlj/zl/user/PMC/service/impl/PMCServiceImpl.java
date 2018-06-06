package com.zlj.zl.user.PMC.service.impl;

import com.zlj.zl.user.PMC.jpa.PMCJpa;
import com.zlj.zl.user.PMC.model.PMCModel;
import com.zlj.zl.user.PMC.service.PMCService;
import com.zlj.zl.util.resultJson.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
@Service
@Transactional
public class PMCServiceImpl implements PMCService {

    @Autowired
    private PMCJpa jpa;

    @Override
    public ResponseResult<Page<PMCModel>> findByPageAndParams(PMCModel param, int pageNumber, int pageSize) {
        Pageable pageable = new PageRequest(pageNumber, pageSize);  //分页信息
        Specification<PMCModel> spec = new Specification<PMCModel>() {        //查询条件构造
            @Override
            public Predicate toPredicate(Root<PMCModel> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate p1 = cb.like(root.get("names").as(String.class), "%" + param.getNames() + "%");
                Predicate p2 = cb.equal(root.get("types").as(Integer.class), param.getTypes());

                if ((param.getNames() != null && !param.getNames().equals(""))
                        && param.getTypes() > 0) {
                    Predicate p = cb.and(p1, p2);
                    return p;
                } else {
                    if (param.getNames() != null && !param.getNames().equals("")) {
                        Predicate p = cb.and(p1);
                        return p;
                    } else if (param.getTypes() > 0) {
                        Predicate p = cb.and(p2);
                        return p;
                    } else {
                        return null;
                    }
                }
            }
        };
        Page<PMCModel> page = jpa.findAll(spec, pageable);
        ResponseResult<Page<PMCModel>> result = new ResponseResult<>();
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
    public ResponseResult<List<PMCModel>> findByPageAndParamsList(PMCModel param) {
        Specification<PMCModel> spec = new Specification<PMCModel>() {//查询条件构造
            @Override
            public Predicate toPredicate(Root<PMCModel> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate p1 = cb.like(root.get("names").as(String.class), "%" + param.getNames() + "%");
                Predicate p2 = cb.equal(root.get("types").as(Integer.class), param.getTypes());
                Predicate p3 = cb.equal(root.get("patentUuid").as(String.class), param.getPatentUuid());

                if (param.getNames() != null && !param.getNames().equals("")) {
                    Predicate p = cb.and(p1);
                    return p;
                }
                if (param.getTypes() > 0) {
                    Predicate p = cb.and(p2);
                    return p;
                }
                if (param.getPatentUuid() != null && !param.getPatentUuid().equals("")) {
                    Predicate p = cb.and(p3);
                    return p;
                }
                return null;
            }
        };
        List<PMCModel> list = jpa.findAll(spec);
        ResponseResult<List<PMCModel>> result = new ResponseResult<>();
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

    @Override
    public ResponseResult<PMCModel> save(PMCModel model) {
        ResponseResult<PMCModel> result = new ResponseResult<>();
        ResponseResult<List<PMCModel>> list = findByPageAndParamsList(new PMCModel(null, model.getNames(), 0, null));
        if (list.isSuccess()) {
            result.setSuccess(false);
            result.setMessage("此数据已存在");
            return result;
        } else {
            jpa.save(model);
            result.setSuccess(true);
            result.setMessage("成功");
            return result;
        }
    }

    @Override
    public ResponseResult<PMCModel> delete(String uuid) {
        ResponseResult<PMCModel> result = new ResponseResult<>();
        ResponseResult<List<PMCModel>> list = findByPageAndParamsList(new PMCModel(null, null, 0, uuid));
        if (list.isSuccess()) {
            result.setSuccess(false);
            result.setMessage("请先删除下级数据");
            return result;
        }
        jpa.delete(uuid);
        result.setSuccess(true);
        result.setMessage("成功");
        return result;
    }

    @Override
    public ResponseResult<PMCModel> update(PMCModel model) {
        ResponseResult<PMCModel> result = new ResponseResult<>();
        jpa.update(model.getNames(), model.getUuid());
        result.setSuccess(true);
        result.setMessage("成功");
        return result;
    }

    @Override
    public List<PMCModel> findByCityName(String names) {
        return jpa.findByCityName(names);
    }
}
