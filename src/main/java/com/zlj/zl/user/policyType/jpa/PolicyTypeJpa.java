package com.zlj.zl.user.policyType.jpa;

import com.zlj.zl.user.policyType.model.PolicyTypeModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
public interface PolicyTypeJpa extends JpaRepository<PolicyTypeModel, String> {

    String table = " policy_type_table ";

    @Modifying
    @Query(value = "update " + table + " set type_name = ?2 where uuid = ?1", nativeQuery = true)
    @Transactional
    void update(String uuid, String typeName);

    @Query(value = "select * from " + table + " where type_name = ?1", nativeQuery = true)
    List<PolicyTypeModel> findByTypeName(String typeName);

    //    源生sql分页
    @Query(nativeQuery = true, value = "select * from " + table + " ORDER BY ?#{#pageable},type_name asc"
            , countQuery = "select count(*) from " + table)
    Page<PolicyTypeModel> findAllByPageable(Pageable pageable);
}
