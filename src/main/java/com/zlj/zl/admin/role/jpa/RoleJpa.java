package com.zlj.zl.admin.role.jpa;

import com.zlj.zl.admin.role.model.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
public interface RoleJpa extends JpaRepository<RoleModel, String> {

    String table = " role_table ";

    @Modifying
    @Query(value = "update " + table + " set name = ?1 where uuid = ?2", nativeQuery = true)
    int update(String name, String uuid);

    @Query(value = "select * from " + table, nativeQuery = true)
    List<RoleModel> page();

    @Query(value = "select * from " + table + " where name = ?!", nativeQuery = true)
    List<RoleModel> pageByName(String seach);
}
