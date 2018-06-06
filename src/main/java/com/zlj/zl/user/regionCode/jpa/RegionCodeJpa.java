package com.zlj.zl.user.regionCode.jpa;

import com.zlj.zl.user.regionCode.model.RegionCodeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
public interface RegionCodeJpa extends JpaSpecificationExecutor<RegionCodeModel>, JpaRepository<RegionCodeModel, String> {
}
