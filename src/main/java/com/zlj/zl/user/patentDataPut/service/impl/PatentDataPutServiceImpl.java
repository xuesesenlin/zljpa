package com.zlj.zl.user.patentDataPut.service.impl;

import com.zlj.zl.user.patentDataPut.jpa.PatentDataPutJpa;
import com.zlj.zl.user.patentDataPut.model.PatentDataPutModel;
import com.zlj.zl.user.patentDataPut.model.PatentDataPutQueryModel;
import com.zlj.zl.user.patentDataPut.service.PatentDataPutService;
import com.zlj.zl.util.resultJson.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
@Service
@Transactional
public class PatentDataPutServiceImpl implements PatentDataPutService {

    SimpleDateFormat sdfmat = new SimpleDateFormat("yyyy-MM");
    SimpleDateFormat sdfmat2 = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    private PatentDataPutJpa jpa;
    @PersistenceContext
    protected EntityManager em;

    @Override
    public ResponseResult<PatentDataPutModel> saves(List<PatentDataPutModel> list) {
        jpa.save(list);
        return new ResponseResult<>(true, "成功", null);
    }

    @Override
    public ResponseResult<PatentDataPutModel> delete(String uuid) {
        PatentDataPutModel one = jpa.getOne(uuid);
        if (one.getLocking().equals("Y"))
            return new ResponseResult<>(false, "数据已锁定", null);
        jpa.delete(uuid);
        return new ResponseResult<>(true, "成功", null);
    }

    @Override
    public ResponseResult<PatentDataPutModel> update(PatentDataPutModel model) {
//        此处必须用getOne不能用findOne，get为持久实体,find为返回值
        PatentDataPutModel putModel = jpa.getOne(model.getUuid());
        if (putModel.getLocking().equals("Y"))
            return new ResponseResult<>(false, "数据已锁定", null);
        if (model.getArea() != null && !model.getArea().trim().equals(""))
            putModel.setArea(model.getArea());
        if (model.getEliminationZero() != null && !model.getEliminationZero().trim().equals(""))
            putModel.setEliminationZero(model.getEliminationZero());
        return new ResponseResult<>(true, "成功", null);
    }

    @Override
    public ResponseResult<PatentDataPutModel> updates(List<PatentDataPutModel> model, int lx) {
        if (lx != 2) {
            StringJoiner s = new StringJoiner("");
            model.forEach(k -> {
                PatentDataPutModel one = jpa.getOne(k.getUuid());
                if (one.getLocking().equals("Y")) {
                    s.add("1");
                    return;
                }
                one.setFlag("-1");
            });
            if (s.toString().equals("1"))
                return new ResponseResult<>(false, "锁定数据删除失败", null);
            else
                return new ResponseResult<>(true, "成功", null);
        } else {
            model.forEach(k -> {
                PatentDataPutModel one = jpa.getOne(k.getUuid());
                one.setLocking(k.getLocking());
            });
            return new ResponseResult<>(true, "成功", null);
        }
    }

    @Override
    public ResponseResult<Page<PatentDataPutModel>> page(PatentDataPutQueryModel model, int pageNow, int pageSize) {
        List<Sort.Order> orders = new ArrayList<>();
        orders.add(new Sort.Order(Sort.Direction.DESC, "applyDate"));//排序信息
        Specification<PatentDataPutModel> spec = queryTj(model);
//jpa分页
        Pageable pageable = new PageRequest(pageNow, pageSize, new Sort(orders));  //分页信息
        Page<PatentDataPutModel> page = jpa.findAll(spec, pageable);
        ResponseResult<Page<PatentDataPutModel>> result = new ResponseResult<>();
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
    public ResponseResult<List<PatentDataPutModel>> list(PatentDataPutQueryModel model) {
        List<Sort.Order> orders = new ArrayList<>();
        orders.add(new Sort.Order(Sort.Direction.DESC, "applyDate"));//排序信息
        Specification<PatentDataPutModel> spec = queryTj(model);
        List<PatentDataPutModel> list = jpa.findAll(spec, new Sort(orders));
        ResponseResult<List<PatentDataPutModel>> result = new ResponseResult<>();
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

    //    查询条件
    private Specification<PatentDataPutModel> queryTj(PatentDataPutQueryModel model) {
        return new Specification<PatentDataPutModel>() {//查询条件构造
            @Override
            public Predicate toPredicate(Root<PatentDataPutModel> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if (model.getSqsqzt() != null && !model.getSqsqzt().trim().equals("")) {
                    Predicate p1 = cb.like(root.get("applyAuthorization").as(String.class), "%" + model.getSqsqzt() + "%");
                    predicates.add(cb.and(p1));
                }
                if (model.getDrnyStar() != null && !model.getDrnyStar().trim().equals("")) {
//                    时间   小于等于 导入日期 大于等于 导入日期
                    try {
                        Predicate p1 = cb.greaterThanOrEqualTo(root.get("impDate").as(Long.class),
                                sdfmat.parse(model.getDrnyStar()).getTime());
                        predicates.add(cb.and(p1));
                    } catch (Exception e) {
                        return null;
                    }
                }
                if (model.getDrnyEnd() != null && !model.getDrnyEnd().trim().equals("")) {
//                    时间  小于等于 导入日期
                    try {
                        Predicate p1 = cb.lessThanOrEqualTo(root.get("impDate").as(Long.class),
                                sdfmat.parse(model.getDrnyEnd()).getTime());
                        predicates.add(cb.and(p1));
                    } catch (Exception e) {
                        return null;
                    }
                }
                if (model.getZlsqh() != null && !model.getZlsqh().trim().equals("")) {
//                    专利申请号
                    Predicate p1 = cb.equal(root.get("applyCode").as(String.class), model.getZlsqh());
                    predicates.add(cb.and(p1));
                }
                if (model.getZflh() != null && !model.getZflh().trim().equals("")) {
//                    主分类号
                    Predicate p1 = cb.equal(root.get("mainTypes").as(String.class), model.getZflh());
                    predicates.add(cb.and(p1));
                }
                if (model.getZllx() != null && !model.getZllx().trim().equals("")) {
//                    专利类型
                    Predicate p1 = cb.equal(root.get("patentTypes").as(String.class), model.getZllx());
                    predicates.add(cb.and(p1));
                }
                if (model.getSqrqStar() != null && !model.getSqrqStar().trim().equals("")) {
//                    申请日期开始
                    try {
                        Predicate p1 = cb.greaterThanOrEqualTo(root.get("applyDate").as(Long.class),
                                sdfmat2.parse(model.getSqrqStar()).getTime());
                        predicates.add(cb.and(p1));
                    } catch (Exception e) {
                        return null;
                    }
                }
                if (model.getSqrqEnd() != null && !model.getSqrqEnd().trim().equals("")) {
//                    申请日期结束
                    try {
                        Predicate p1 = cb.lessThanOrEqualTo(root.get("applyDate").as(Long.class),
                                sdfmat2.parse(model.getSqrqEnd()).getTime());
                        predicates.add(cb.and(p1));
                    } catch (Exception e) {
                        return null;
                    }
                }
                if (model.getSqrdz() != null && !model.getSqrdz().trim().equals("")) {
//                    申请人地址
                    Predicate p1 = cb.like(root.get("peoAddress").as(String.class), "%" + model.getSqrdz() + "%");
                    predicates.add(cb.and(p1));
                }
                if (model.getSqrlx() != null && !model.getSqrlx().trim().equals("")) {
//                    申请人类型
                    Predicate p1 = cb.equal(root.get("appPeoTypes").as(String.class), model.getSqrlx());
                    predicates.add(cb.and(p1));
                }
                if (model.getSzss() != null && !model.getSzss().trim().equals("")) {
//                    所在省
                    Predicate p1 = cb.equal(root.get("province").as(String.class), model.getSzss());
                    predicates.add(cb.and(p1));
                }
                if (model.getSzsc() != null && !model.getSzsc().trim().equals("")) {
//                    所在市
                    Predicate p1 = cb.equal(root.get("city").as(String.class), model.getSzsc());
                    predicates.add(cb.and(p1));
                }
                if (model.getSzsx() != null && !model.getSzsx().trim().equals("")) {
//                    所在区/县
                    Predicate p1 = cb.equal(root.get("area").as(String.class), model.getSzsx());
                    predicates.add(cb.and(p1));
                }
                if (model.getAjrkrStar() != null && !model.getAjrkrStar().trim().equals("")) {
//                    案卷入库日期开始
                    try {
                        Predicate p1 = cb.greaterThanOrEqualTo(root.get("fileEnterDate").as(Long.class),
                                sdfmat2.parse(model.getAjrkrStar()).getTime());
                        predicates.add(cb.and(p1));
                    } catch (Exception e) {
                        return null;
                    }
                }
                if (model.getAjrkrEnd() != null && !model.getAjrkrEnd().trim().equals("")) {
//                    案卷入库日期结束
                    try {
                        Predicate p1 = cb.lessThanOrEqualTo(root.get("fileEnterDate").as(Long.class),
                                sdfmat2.parse(model.getAjrkrEnd()).getTime());
                        predicates.add(cb.and(p1));
                    } catch (Exception e) {
                        return null;
                    }
                }

//                企业类型
                if (model.getQylx() != null && !model.getQylx().trim().equals("")) {
                    Predicate p1 = cb.like(root.get("companyType").as(String.class),
                            "%" + model.getQylx() + "%");
                    predicates.add(cb.and(p1));
                }
//                是否消零
                if (model.getSfxl() != null && !model.getSfxl().trim().equals("")) {
                    Predicate p1 = cb.equal(root.get("eliminationZero").as(String.class),
                            model.getSfxl());
                    predicates.add(cb.and(p1));
                }

                Predicate p1 = cb.notEqual(root.get("flag").as(String.class), "-1");
                predicates.add(cb.and(p1));

                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }

    @Override
    public ResponseResult<List<PatentDataPutModel>> findByApplyCode(String applyCode) {
        List<PatentDataPutModel> list = jpa.findByApplyCode(applyCode);
        if (list.size() > 0)
            return new ResponseResult<>(true, "成功", null);
        else
            return new ResponseResult<>(false, "未查询到数据", null);
    }

    @Override
    public ResponseResult<List<PatentDataPutModel>> findByImpDate(long impDate) {
        List<PatentDataPutModel> list = jpa.findByImpDate(impDate);
        if (list.size() > 0)
            return new ResponseResult<>(true, "成功", list);
        else return new ResponseResult<>(false, "未查询到数据", null);
    }
}
