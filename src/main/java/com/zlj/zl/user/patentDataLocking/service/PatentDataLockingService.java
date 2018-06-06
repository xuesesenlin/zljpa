package com.zlj.zl.user.patentDataLocking.service;

import com.zlj.zl.util.resultJson.ResponseResult;

import java.util.List;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
public interface PatentDataLockingService {

    ResponseResult<List<Object[]>> findByImpDate(int pageNow, int pageSize, String drnyStar, String drnyEnd);

}
