package com.zlj.zl.user.regionCode.service;

import com.zlj.zl.user.regionCode.model.RegionCodeModel;
import com.zlj.zl.util.resultJson.ResponseResult;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
public interface RegionCodeService {

    ResponseResult<RegionCodeModel> put(RegionCodeModel model);

    ResponseResult<RegionCodeModel> delete(String uuid);

    ResponseResult<Page<RegionCodeModel>> page(int pageNumber, int pageSize);

    ResponseResult<List<RegionCodeModel>> list(final RegionCodeModel model);
}
