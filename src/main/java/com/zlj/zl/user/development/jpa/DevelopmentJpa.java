package com.zlj.zl.user.development.jpa;

import com.zlj.zl.user.development.model.DevelopmentModel;
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
public interface DevelopmentJpa extends JpaRepository<DevelopmentModel, String> {

    String table = " development_table ";

    @Modifying
    @Query(value = "update " + table + " set titles = ?1,bodys=?2 where uuid = ?3", nativeQuery = true)
    @Transactional
    void update(String titles, String bodys, String uuid);

    //    源生sql分页
    @Query(nativeQuery = true, value = "select * from " + table + " where titles like ?1 ORDER BY ?#{#pageable},titles asc"
            , countQuery = "select count(*) from " + table + " where titles like ?1")
    Page<DevelopmentModel> findAllByPageable(String titles, Pageable pageable);

}
