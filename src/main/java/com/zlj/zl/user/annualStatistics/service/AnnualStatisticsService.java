package com.zlj.zl.user.annualStatistics.service;

import com.zlj.zl.util.resultJson.ResponseResult;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
public interface AnnualStatisticsService {

    ResponseResult<String> exp(String basePath, String outPath, String data) throws Exception;
}
