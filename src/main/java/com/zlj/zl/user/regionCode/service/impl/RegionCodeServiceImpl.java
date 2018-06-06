package com.zlj.zl.user.regionCode.service.impl;

import com.zlj.zl.user.regionCode.jpa.RegionCodeJpa;
import com.zlj.zl.user.regionCode.model.RegionCodeModel;
import com.zlj.zl.user.regionCode.service.RegionCodeService;
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
public class RegionCodeServiceImpl implements RegionCodeService {

    @Autowired
    private RegionCodeJpa jpa;

    @Override
    public ResponseResult<RegionCodeModel> put(RegionCodeModel model) {
        ResponseResult<RegionCodeModel> result = new ResponseResult<>();
        if (model.getUuid() == null || model.getUuid().equals(""))
            jpa.save(model);
        else {
            RegionCodeModel model1 = jpa.getOne(model.getUuid());
            model1.setKeyworda(model.getKeyworda());
            model1.setKeywordb(model.getKeywordb());
        }
        result.setSuccess(true);
        result.setMessage("成功");
        return result;
    }

    @Override
    public ResponseResult<RegionCodeModel> delete(String uuid) {
        ResponseResult<RegionCodeModel> result = new ResponseResult<>();
        jpa.delete(uuid);
        result.setSuccess(true);
        result.setMessage("成功");
        return result;
    }

    @Override
    public ResponseResult<Page<RegionCodeModel>> page(int pageNumber, int pageSize) {
        List<Sort.Order> orders = new ArrayList<>();
        orders.add(new Sort.Order(Sort.Direction.DESC, "oneLevel"));//排序信息
        Pageable pageable = new PageRequest(pageNumber, pageSize, new Sort(orders));  //分页信息
//jpa分页
        Page<RegionCodeModel> page = jpa.findAll(pageable);
        ResponseResult<Page<RegionCodeModel>> result = new ResponseResult<>();
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
    public ResponseResult<List<RegionCodeModel>> list(RegionCodeModel model) {
        ResponseResult<List<RegionCodeModel>> result = new ResponseResult<>();
        Specification<RegionCodeModel> spec = new Specification<RegionCodeModel>() {        //查询条件构造
            @Override
            public Predicate toPredicate(Root<RegionCodeModel> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                if (model.getArea() != null && !model.getArea().trim().equals("")) {
                    Predicate p1 = cb.equal(root.get("area").as(String.class), model.getArea());
                    Predicate p = cb.and(p1);
                    return p;
                } else
                    return null;
            }
        };
        List<RegionCodeModel> list = jpa.findAll(spec);
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
