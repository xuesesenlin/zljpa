package com.zlj.zl.admin.organization.jpa;

import com.zlj.zl.admin.organization.model.OrganizationModel;
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
public interface OrganizationJpa extends JpaRepository<OrganizationModel, String> {

    String table = " organization_table ";

    @Modifying
    @Query(value = "update " + table + " set name = ?1 where uuid = ?2", nativeQuery = true)
    int update(String name, String uuid);

    @Query(value = "select * from " + table + " where parents = ?1", nativeQuery = true)
    List<OrganizationModel> findByParents(String parents);
}
