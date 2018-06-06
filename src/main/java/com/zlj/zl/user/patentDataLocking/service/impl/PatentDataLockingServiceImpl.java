package com.zlj.zl.user.patentDataLocking.service.impl;

import com.zlj.zl.user.patentDataLocking.service.PatentDataLockingService;
import com.zlj.zl.user.patentDataPut.jpa.PatentDataPutJpa;
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
public class PatentDataLockingServiceImpl implements PatentDataLockingService {

    @Autowired
    private PatentDataPutJpa jpa;

    @Override
    public ResponseResult<List<Object[]>> findByImpDate(int pageNow, int pageSize, String drnyStar, String drnyEnd) {
        if (pageNow < 0)
            pageNow = 0;
        if (pageNow > 0)
            pageNow = pageNow * pageSize + 1;
        if ((drnyStar == null || drnyStar.trim().equals("")) || (drnyEnd == null || drnyEnd.trim().equals(""))) {
            List<Object[]> list = jpa.findDate(pageNow, pageSize);
            if (list.size() > 0)
                return new ResponseResult<>(true, "成功", list);
            else
                return new ResponseResult<>(false, "未查询到数据", null);
        } else {
            try {
                List<Object[]> list = jpa.findDateByDate(pageNow
                        , pageSize
                        , drnyStar
                        , drnyEnd);
                if (list.size() > 0)
                    return new ResponseResult<>(true, "成功", list);
                else
                    return new ResponseResult<>(false, "未查询到数据", null);
            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseResult<>(false, "未查询到数据", null);
            }
        }
    }
}
