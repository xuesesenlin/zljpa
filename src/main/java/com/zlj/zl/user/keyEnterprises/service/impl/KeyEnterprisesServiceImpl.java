package com.zlj.zl.user.keyEnterprises.service.impl;

import com.zlj.zl.user.keyEnterprises.jpa.KeyEnterprisesJpa;
import com.zlj.zl.user.keyEnterprises.model.KeyEnterprisesModel;
import com.zlj.zl.user.keyEnterprises.service.KeyEnterprisesService;
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
public class KeyEnterprisesServiceImpl implements KeyEnterprisesService {

    @Autowired
    private KeyEnterprisesJpa jpa;

    @Override
    public ResponseResult<KeyEnterprisesModel> put(KeyEnterprisesModel model) {
        ResponseResult<KeyEnterprisesModel> result = new ResponseResult<>();
        if (model.getUuid() == null || model.getUuid().equals(""))
            jpa.save(model);
        else {
            KeyEnterprisesModel model1 = jpa.getOne(model.getUuid());
            model1.setKeyworda(model.getKeyworda());
            model1.setKeywordb(model.getKeywordb());
        }
        result.setSuccess(true);
        result.setMessage("成功");
        return result;
    }

    @Override
    public ResponseResult<KeyEnterprisesModel> delete(String uuid) {
        ResponseResult<KeyEnterprisesModel> result = new ResponseResult<>();
        jpa.delete(uuid);
        result.setSuccess(true);
        result.setMessage("成功");
        return result;
    }

    @Override
    public ResponseResult<Page<KeyEnterprisesModel>> page(int pageNumber, int pageSize) {
        List<Sort.Order> orders = new ArrayList<>();
        orders.add(new Sort.Order(Sort.Direction.DESC, "oneLevel"));//排序信息
        Pageable pageable = new PageRequest(pageNumber, pageSize, new Sort(orders));  //分页信息
//jpa分页
        Page<KeyEnterprisesModel> page = jpa.findAll(pageable);
        ResponseResult<Page<KeyEnterprisesModel>> result = new ResponseResult<>();
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
    public ResponseResult<List<KeyEnterprisesModel>> list(KeyEnterprisesModel model) {
        ResponseResult<List<KeyEnterprisesModel>> result = new ResponseResult<>();
        Specification<KeyEnterprisesModel> spec = new Specification<KeyEnterprisesModel>() {        //查询条件构造
            @Override
            public Predicate toPredicate(Root<KeyEnterprisesModel> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                if (model.getQylx() != null && !model.getQylx().trim().equals("")) {
                    Predicate p1 = cb.equal(root.get("qylx").as(String.class), model.getQylx());
                    Predicate p = cb.and(p1);
                    return p;
                } else
                    return null;
            }
        };
        List<KeyEnterprisesModel> list = jpa.findAll(spec);
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
