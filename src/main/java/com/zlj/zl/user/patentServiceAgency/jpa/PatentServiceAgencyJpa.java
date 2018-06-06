package com.zlj.zl.user.patentServiceAgency.jpa;

import com.zlj.zl.user.patentServiceAgency.model.PatentServiceAgencyModel;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
public interface PatentServiceAgencyJpa extends JpaRepository<PatentServiceAgencyModel, String> {
}
