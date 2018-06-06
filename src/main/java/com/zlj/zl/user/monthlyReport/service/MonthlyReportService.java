package com.zlj.zl.user.monthlyReport.service;

import com.zlj.zl.util.resultJson.ResponseResult;

import java.util.List;
import java.util.Map;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
public interface MonthlyReportService {

    ResponseResult<Map<String, List<Object[]>>> findDate(String impDate);

    ResponseResult<Map<String, List<Object[]>>> findDate2(String impDate);

    ResponseResult<String> exp(String basePath, String outPath, String data) throws Exception;

    ResponseResult<String> exp2(String basePath, String outPath, String data) throws Exception;
}
