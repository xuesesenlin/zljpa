package com.zlj.zl.admin.organAcc.jpa;

import com.zlj.zl.admin.organAcc.model.OrganAccModel;
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
public interface OrganAccJpa extends JpaRepository<OrganAccModel, String> {

    String table = " organ_acc_table ";

    @Modifying
    @Query(value = "update " + table + " set flag = '-1' where uuid = ?1", nativeQuery = true)
    int del(String id);

    @Query(value = "select * from " + table + " where acc_id = ?1 and flag = '0'", nativeQuery = true)
    List<OrganAccModel> findByAccId(String accId);

    @Query(value = "select o.uuid,a.account as acc_id,o.org_id,o.flag from organ_acc_table o " +
            "left join account_table a on a.uuid = o.acc_id where o.org_id = ?1 and o.flag = '0'", nativeQuery = true)
    List<OrganAccModel> findByOrganId(String organId);

}
