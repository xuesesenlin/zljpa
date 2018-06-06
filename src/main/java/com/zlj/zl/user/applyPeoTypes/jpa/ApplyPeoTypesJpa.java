package com.zlj.zl.user.applyPeoTypes.jpa;

import com.zlj.zl.user.applyPeoTypes.model.ApplyPeoTypesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
public interface ApplyPeoTypesJpa extends JpaSpecificationExecutor<ApplyPeoTypesModel>, JpaRepository<ApplyPeoTypesModel, String> {
}
