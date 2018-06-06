package com.zlj.zl.user.county.service;

import com.zlj.zl.util.resultJson.ResponseResult;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
public interface CountyService {

    ResponseResult<String> exp(String basePath, String outPath, String a, String b, String c) throws Exception;
}
