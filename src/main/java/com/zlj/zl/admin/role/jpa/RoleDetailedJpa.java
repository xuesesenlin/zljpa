package com.zlj.zl.admin.role.jpa;

import com.zlj.zl.admin.role.model.RoleDetailedModel;
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
public interface RoleDetailedJpa extends JpaRepository<RoleDetailedModel, String> {

    String table = " role_detailed_table ";

    @Modifying
    @Query(value = "delete from " + table + " where uuid = ?1", nativeQuery = true)
    int del(String id);

    @Modifying
    @Query(value = "delete from " + table + " where role_id = ?1 and jur_id = ?2", nativeQuery = true)
    @Transactional
    int delByJurIdAndRoleId(String roleId, String jurId);

    @Query(value = "select r.uuid,jur_id,role_id from role_detailed_table r left join jurisdiction_table j " +
            "on j.uuid = r.jur_id where role_id = ?1", nativeQuery = true)
    List<RoleDetailedModel> findByRoleId(String roleId);

    @Query(value = "select r.uuid,jur_id,role_id from role_detailed_table r left join jurisdiction_table j " +
            "on j.uuid = r.jur_id where role_id = ?1 and jur_id = ?2", nativeQuery = true)
    List<RoleDetailedModel> findByRoleIdAndJurId(String roleId, String jurId);

}
