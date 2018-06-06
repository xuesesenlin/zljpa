package com.zlj.zl.user.policy.jpa;

import com.zlj.zl.user.policy.model.PolicyModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
public interface PolicyJpa extends JpaRepository<PolicyModel, String> {

    String table = " policy_table ";

    @Modifying
    @Query(value = "update " + table + " set types = ?1,titles = ?2,bodys=?3 where uuid = ?4", nativeQuery = true)
    @Transactional
    void update(String types, String titles, String bodys, String uuid);

    //
//    @Query(value = "select * from " + table + " where type_name = ?1", nativeQuery = true)
//    List<PolicyTypeModel> findByTypeName(String typeName);
//
    //    源生sql分页
    @Query(nativeQuery = true, value = "select * from " + table + " where titles like ?1 and types = ?2 ORDER BY ?#{#pageable},titles asc"
            , countQuery = "select count(*) from " + table + " where titles like ?1 and types = ?2")
    Page<PolicyModel> findAllByPageable(String titles, String types, Pageable pageable);

    @Query(nativeQuery = true, value = "select * from " + table + " where titles like ?1 ORDER BY ?#{#pageable},titles asc"
            , countQuery = "select count(*) from " + table + " where titles like ?1")
    Page<PolicyModel> findAllByPageable2(String titles, Pageable pageable);

    @Query(nativeQuery = true, value = "select * from " + table + " where types = ?1 ORDER BY ?#{#pageable},titles asc"
            , countQuery = "select count(*) from " + table + " where types = ?1")
    Page<PolicyModel> findAllByPageable3(String types, Pageable pageable);
}
