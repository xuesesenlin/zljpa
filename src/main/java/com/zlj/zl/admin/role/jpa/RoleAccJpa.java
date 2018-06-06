package com.zlj.zl.admin.role.jpa;

import com.zlj.zl.admin.role.model.RoleAccModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
public interface RoleAccJpa extends JpaRepository<RoleAccModel, String> {

    String table = " role_acc_table ";

    @Modifying
    @Query(value = "delete from " + table + " where account_id = ?1", nativeQuery = true)
    @Transactional
    int delByAccountId(String id);

    @Query(value = "select * from " + table + " where account_id = ?1", nativeQuery = true)
    List<RoleAccModel> findByAccountId(String accountId);

    @Query(value = "select * from " + table + " where role_id = ?1", nativeQuery = true)
    List<RoleAccModel> findByRoleId(String roleId);
}
