package com.zlj.zl.publics.logger.jpa;

import com.zlj.zl.publics.logger.model.LoggerModel;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
public interface LoggerJpa extends JpaRepository<LoggerModel, String> {
}
