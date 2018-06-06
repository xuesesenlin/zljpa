package com.zlj.zl.user.patentTypes.jpa;

import com.zlj.zl.user.patentTypes.model.PatentTypesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
public interface PatentTypesJpa extends JpaSpecificationExecutor<PatentTypesModel>, JpaRepository<PatentTypesModel, String> {
}
