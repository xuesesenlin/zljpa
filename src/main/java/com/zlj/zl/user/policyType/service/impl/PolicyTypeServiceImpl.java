package com.zlj.zl.user.policyType.service.impl;

import com.zlj.zl.user.policyType.jpa.PolicyTypeJpa;
import com.zlj.zl.user.policyType.model.PolicyTypeModel;
import com.zlj.zl.user.policyType.service.PolicyTypeService;
import com.zlj.zl.util.resultJson.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
public class PolicyTypeServiceImpl implements PolicyTypeService {

    @Autowired
    private PolicyTypeJpa jpa;

    @Override
    public ResponseResult<PolicyTypeModel> add(PolicyTypeModel model) {
        ResponseResult<PolicyTypeModel> result = new ResponseResult<>();
        jpa.save(model);
        result.setSuccess(true);
        result.setMessage("成功");
        return result;
    }

    @Override
    public ResponseResult<PolicyTypeModel> del(String id) {
        ResponseResult<PolicyTypeModel> result = new ResponseResult<>();
        jpa.delete(id);
        result.setSuccess(true);
        result.setMessage("成功");
        return result;
    }

    @Override
    public ResponseResult<PolicyTypeModel> update(PolicyTypeModel model) {
        ResponseResult<PolicyTypeModel> result = new ResponseResult<>();
        jpa.update(model.getUuid(), model.getTypeName());
        result.setSuccess(true);
        result.setMessage("成功");
        return result;
    }

    @Override
    public ResponseResult<List<PolicyTypeModel>> findByTypeName(String typeName) {
        ResponseResult<List<PolicyTypeModel>> result = new ResponseResult<>();
        List<PolicyTypeModel> list = jpa.findByTypeName(typeName);
        if (list.size() > 0) {
            result.setSuccess(true);
            result.setMessage("成功");
            result.setData(list);
            return result;
        } else {
            result.setSuccess(false);
            result.setMessage("未查询到数据");
            result.setData(list);
            return result;
        }
    }

    @Override
    public ResponseResult<Page<PolicyTypeModel>> page(int pageNow, int pageSize) {
        ResponseResult<Page<PolicyTypeModel>> result = new ResponseResult<>();
        Pageable pageable = new PageRequest(pageNow, pageSize);
//        jpa版分页
//        Page<PolicyTypeModel> page = jpa.findAll(pageable);
        Page<PolicyTypeModel> page = jpa.findAllByPageable(pageable);
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

//        Page<Book> bookPage = bookRepository.findAll(new Specification<Book>(){
//            @Override
//            public Predicate toPredicate(Root<Book> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
//                List<Predicate> list = new ArrayList<Predicate>();
//                if(null!=bookQuery.getName()&&!"".equals(bookQuery.getName())){
//                    list.add(criteriaBuilder.equal(root.get("name").as(String.class), bookQuery.getName()));
//                }
//                if(null!=bookQuery.getIsbn()&&!"".equals(bookQuery.getIsbn())){
//                    list.add(criteriaBuilder.equal(root.get("isbn").as(String.class), bookQuery.getIsbn()));
//                }
//                if(null!=bookQuery.getAuthor()&&!"".equals(bookQuery.getAuthor())){
//                    list.add(criteriaBuilder.equal(root.get("author").as(String.class), bookQuery.getAuthor()));
//                }
//                Predicate[] p = new Predicate[list.size()];
//                return criteriaBuilder.and(list.toArray(p));
//            }
//        },pageable);
    }
}
