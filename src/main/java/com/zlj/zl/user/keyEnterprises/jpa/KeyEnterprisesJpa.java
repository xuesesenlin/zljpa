package com.zlj.zl.user.keyEnterprises.jpa;

import com.zlj.zl.user.keyEnterprises.model.KeyEnterprisesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
public interface KeyEnterprisesJpa extends JpaSpecificationExecutor<KeyEnterprisesModel>, JpaRepository<KeyEnterprisesModel, String> {
}
